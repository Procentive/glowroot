/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.local.ui;

import java.lang.annotation.Retention;

import io.netty.handler.codec.http.HttpResponseStatus;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

// marker annotation
@interface JsonService {}

// similar to javax.ws.rs.GET
@Retention(RUNTIME)
@interface GET {
    String value();
}

// similar to javax.ws.rs.POST
@Retention(RUNTIME)
@interface POST {
    String value();
}

// used for "expected" exceptions, these are not logged on server and their stack trace is not sent
// back to client
@SuppressWarnings("serial")
class JsonServiceException extends RuntimeException {

    private final HttpResponseStatus status;

    JsonServiceException(HttpResponseStatus status, Throwable cause) {
        super("", cause);
        this.status = status;
    }

    JsonServiceException(HttpResponseStatus status, String message) {
        super(message);
        this.status = status;
    }

    JsonServiceException(HttpResponseStatus status) {
        super();
        this.status = status;
    }

    HttpResponseStatus getStatus() {
        return status;
    }
}
