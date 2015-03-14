package org.paxml.util;

import java.util.Collection;
import java.util.Map;

import org.paxml.core.PaxmlRuntimeException;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XStreamMapColConverter implements Converter {
	private final String rootCollectionName;

	public XStreamMapColConverter(String rootCollectionName) {
		this.rootCollectionName = rootCollectionName == null ? "_" : rootCollectionName;
	}

	public boolean canConvert(final Class clazz) {
		return Map.class.isAssignableFrom(clazz) || isCollection(clazz);
	}

	public void marshal(final Object value, final HierarchicalStreamWriter writer, final MarshallingContext context) {
		if (value instanceof Map) {
			marshal((Map) value, writer, context);
		} else if (value instanceof Collection) {
			marshal((Collection) value, writer, context, null);
		} else {
			throw new PaxmlRuntimeException("Should not reach here!");
		}

	}

	static boolean isCollection(Class clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}

	private void marshal(Collection col, HierarchicalStreamWriter writer, MarshallingContext context, String tagName) {
		if (tagName == null) {
			tagName = XStreamBeanConverter.getCurrentPropertyName(context);
			if (tagName == null) {
				tagName = rootCollectionName;
			}
		}
		for (Object v : col) {
			writer.startNode(tagName);
			context.convertAnother(v);
			writer.endNode();
		}
	}

	private void marshal(Map map, HierarchicalStreamWriter writer, MarshallingContext context) {
		for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) map).entrySet()) {
			Object v = entry.getValue();
			String key = String.valueOf(entry.getKey());
			if (v instanceof Map) {
				writer.startNode(key);
				context.convertAnother(v);
				writer.endNode();
			} else if (v instanceof Collection) {
				marshal((Collection) v, writer, context, key);
			} else {
				writer.startNode(key);
				// writer.setValue(entry.getValue().toString());
				context.convertAnother(v);
				writer.endNode();

			}
		}
	}

	public Object unmarshal(final HierarchicalStreamReader reader, final UnmarshallingContext context) {
		throw new PaxmlRuntimeException("Use paxml parser instead!");
	}
}