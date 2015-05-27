package com.voghan.bookstore.domain;


import com.voghan.bookstore.utilities.JsonUtil;

import java.io.Serializable;

/**
 * Convenience class for overriding toString
 */
public abstract class Base implements Serializable {
	private static final long serialVersionUID = 1881783101112267L;

	@Override
    public String toString() {
        return JsonUtil.convert(this);
    }
}
