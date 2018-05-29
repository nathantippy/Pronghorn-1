---
title: "Schemas"
permalink: /catalog/list-schemas
toc: true
---
## Pronghorn

### AlertNoticeSchema
Defines alert notices.

***

### BlockManagerRequestSchema
Defines a request to the block manager.

***

### BlockManagerResponseSchema
Defines block responses.

***

### BlockStorageReceiveSchema
Defines how block storage is received.

***

### ClientHTTPRequestSchema
Defines a client hTTP request. Includes payload, headers, destination, session, port, host, path,<br/>and more required for a functioning HTTP client.

***

### ColumnSchema
Represents a column inside a matrix.

***

### HTTPLogRequestSchema
Defines how HTTP logging of a request works.

***

### HTTPRequestSchema
Defines what a HTTP request looks like. This includes basics such as the verb (GET, POST, etc...), parameters, but lso<br/>request context, channel ID, the binary payload, if it is a REST request, and more.

***

### NetPayloadSchema
Defines a typical payload for the net. This is used by HTTP, MQTT, and more. Fields include if payload<br/>is encrypted, when it arrived, position, connection, a new route if required, and more.

***

### NetResponseSchema
Defines a typical networked response. Includes payload, context, connection, host, port, and more.

***

### RawDataSchema
Defines raw binary data. Basic building block if no other schema is available.

***

### ReleaseSchema
Defines release messages. These are acknowledgments to be sent back to another stage to let them know<br/>that a pipe is free or a task has finished. Use Position and SequenceNo fields to indicate where operation<br/>has ceased.

***

### SequentialCtlSchema
Defines sequential control behavior.

***

### ServerConnectionSchema
Defines how a connection to a server is established.

***

### ServerResponseSchema
Defines how a server response is formatted, including channel information, payload,<br/>request , subscriptions, sequence, and more.

***

### TestDataSchema
Writes int to specified pipe<br/>@param output pipe to write to<br/>@param fieldIntValue int to write

***

## JPG-Raster

### JPGSchema
Defines a JPG image. This includes headers, filename, MCUs, quantization tables, color components,
<br/>and more.

***
