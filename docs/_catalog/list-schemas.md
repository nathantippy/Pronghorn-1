---
title: "Schemas"
permalink: /catalog/list-schemas
toc: true
---
## Pronghorn

### AlertNoticeSchema
Defines alert notices.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="RouteSLA" id="100">

        <uInt64 name="Connection"   id="1"/>
        <uInt64 name="Sequence" id="2"/>
        <uInt64 name="Duration" id="2"/>
        <uInt32 name="Route" id="4"/>

    </template>

</templates>
```

***

### BlockManagerRequestSchema
Defines a request to the block manager.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

     <!-- Common schema for all block support stages
     Two step write, pick block then write to sub block
     This provides 128 bits of addressable space
     -->

     <template name="SubRequest" id="1"> <!-- returns sub address and full route -->
    	 <Int32 name="bits" id="10"/>
     </template>

     <template name="SubRelease" id="2"> <!-- returns ack  -->
         <Int64 name="Id" id="11"/>
         <byteVector name="Route" id="14"/>
     </template>

     <template name="SubWrite" id="3"> <!-- returns ack, assumes route already set.  -->
         <Int64 name="Id" id="11"/>
         <byteVector name="Payload" id="12"/>
     </template>

     <template name="SubRead" id="4"> <!-- returns sub block, assumes route already set  -->
         <Int64 name="id" id="11"/>
     </template>

     <template name="ReadHeader" id="8">
     </template>



     <template name="BlockMount" id="5"> <!-- returns ack  -->
         <byteVector name="Route" id="14"/>
     </template>

     <template name="BlockRequest" id="6"> <!-- returns block ID and route  -->
         <Int32 name="bits" id="10"/>  <!-- new blocks can only be requested by direct parent? -->
     </template>

     <template name="BlockRelease" id="7"> <!-- returns ack  -->
         <byteVector name="Route" id="14"/>
     </template>



</templates>
```

***

### BlockManagerResponseSchema
Defines block responses.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

     <template name="SubAllocated" id="1">
         <Int64 name="Id" id="11"/>
         <byteVector name="Route" id="14"/>
     </template>
     <template name="SubAllocatedFailure" id="21">
         <Int32 name="Status" id="12"/>
     </template>

     <template name="SubReleased" id="2">
     	 <Int32 name="Status" id="12"/> <!-- zero is OK -->
     </template>

     <template name="SubWritten" id="3">
         <Int32 name="Status" id="12"/> <!-- zero is OK -->
     </template>

     <template name="SubRead" id="4">
     	<byteVector name="Payload" id="13"/>
     </template>

     <template name="ReadHeader" id="8">
        <Int64 name="Id" id="11"/>
     	<byteVector name="Payload" id="13"/>
     </template>



     <template name="BlockMounted" id="5">
     	 <Int32 name="Status" id="12"/> <!-- zero is OK -->
     </template>

     <template name="BlockAllocated" id="6">
     	<byteVector name="Route" id="14"/>
     </template>
     <template name="BlockAllocatedFailure" id="26">
         <Int32 name="Status" id="12"/>
     </template>

     <template name="BlockReleased" id="7">
     	<Int32 name="Status" id="12"/> <!-- zero is OK -->
     </template>


</templates>
```

***

### BlockStorageReceiveSchema
Defines how block storage is received.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="Error" id="3">
         <uInt64 name="Position" id="12"/>
    	 <string name="Message" id="10" charset="unicode"/>
   </template>

   <template name="WriteAck" id="2">
    	<uInt64 name="Position" id="12"/>
   </template>

   <template name="DataResponse" id="1">
    	<uInt64 name="Position" id="12"/>
    	<byteVector name="Payload" id="11"/>
   </template>


</templates>
```

***

### ClientHTTPRequestSchema
Defines a client hTTP request. Includes payload, headers, destination, session, port, host, path,<br/>and more required for a functioning HTTP client.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="HTTPGet" id="100">
     <uInt32 name="Destination" id="11"/>
     <uInt32 name="Session" id="10"/>
     <uInt32 name="Port" id="1"/>
     <string name="Host" id="2" charset="unicode"/>
     <string name="Path" id="3" charset="unicode"/>
     <string name="Headers" id="7" charset="unicode"/>
   </template>

    <template name="FastHTTPGet" id="200">
     <uInt32 name="Destination" id="11"/>
     <uInt32 name="Session" id="10"/>
     <uInt32 name="Port" id="1"/>
     <string name="Host" id="2" charset="unicode"/>
     <uInt64 name="ConnectionId" id="20"/>
     <string name="Path" id="3" charset="unicode"/>
     <string name="Headers" id="7" charset="unicode"/>
   </template>

   <template name="HTTPPost" id="101">
     <uInt32 name="Destination" id="11"/>
     <uInt32 name="Session" id="10"/>
     <uInt32 name="Port" id="1"/>
     <string name="Host" id="2" charset="unicode"/>
     <string name="Path" id="3" charset="unicode"/>
     <string name="Headers" id="7" charset="unicode"/>
     <byteVector name="Payload" id="5"/>
   </template>

   <template name="HTTPPostChunked" id="102">
     <uInt32 name="Destination" id="11"/>
     <uInt32 name="Session" id="10"/>
     <uInt32 name="Port" id="1"/>
     <string name="Host" id="2" charset="unicode"/>
     <string name="Path" id="3" charset="unicode"/>
     <uInt64 name="TotalLength" id = "6"/>
     <byteVector name="PayloadChunk" id="5"/>
   </template>

   <template name="HTTPPostChunk" id="103">
     <uInt32 name="Destination" id="11"/>
     <uInt32 name="Session" id="10"/>
     <byteVector name="PayloadChunk" id="5"/>
   </template>

   <template name="Close" id="104">
     <uInt32 name="Session" id="10"/>
   	 <uInt32 name="Port" id="1"/>
     <string name="Host" id="2" charset="unicode"/>
   </template>

   <template name="FastHTTPPost" id="201">
     <uInt32 name="Destination" id="11"/>
     <uInt32 name="Session" id="10"/>
     <uInt32 name="Port" id="1"/>
     <string name="Host" id="2" charset="unicode"/>
     <uInt64 name="ConnectionId" id="20"/>
     <string name="Path" id="3" charset="unicode"/>
     <string name="Headers" id="7" charset="unicode"/>
     <byteVector name="Payload" id="5"/>
   </template>

   <template name="FastClose" id="204">
     <uInt32 name="Session" id="10"/>
   	 <uInt32 name="Port" id="1"/>
     <uInt64 name="ConnectionId" id="20"/>
     <string name="Host" id="2" charset="unicode"/>
   </template>

</templates>
```

***

### ColumnSchema
Represents a column inside a matrix.

***

### HTTPLogRequestSchema
Defines how HTTP logging of a request works.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="Request" id="1">
   		<uInt64 name="Time" id="11"/>
        <uInt64 name="ChannelId"   id="12">
            <delta/>
        </uInt64>
        <int32 name="Sequence" id="13">
        </int32>
        <string name="Head" id="14" charset="unicode"/>

   </template>

</templates>
```

***

### HTTPRequestSchema
Defines what a HTTP request looks like. This includes basics such as the verb (GET, POST, etc...), parameters, but lso<br/>request context, channel ID, the binary payload, if it is a REST request, and more.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="RestRequest" id="300">

        <uInt64 name="ChannelId"   id="21">
            <delta/>
        </uInt64>

        <int32 name="Sequence" id="26">
        </int32>

        <uInt32 name="Verb" id="23">
        </uInt32>

		<byteVector name="Params" id="32"/>

        <uInt32 name="Revision" id="24">
        </uInt32>
        <uInt32 name="RequestContext" id="25">
        </uInt32>
   </template>


   <template name="WebSocketFrame" id="100">

        <uInt64 name="ChannelId"   id="21">
            <delta/>
        </uInt64>

        <int32 name="Sequence" id="26">
        </int32>

        <int32 name="FinOpp" id="11">
        </int32>

        <int32 name="Mask" id="10">
        </int32>

		<byteVector name="BinaryPayload" id="12"/>

   </template>


</templates>
```

***

### NetPayloadSchema
Defines a typical payload for the net. This is used by HTTP, MQTT, and more. Fields include if payload<br/>is encrypted, when it arrived, position, connection, a new route if required, and more.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates>

   <template name="Encrypted" id="200">
     <uInt64 name="ConnectionId" id="201"/>
     <uInt64 name="ArrivalTime" id="210"/>
     <byteVector name="Payload" id="203"/>
   </template>

   <template name="Plain" id="210">
     <uInt64 name="ConnectionId" id="201"/>
     <uInt64 name="ArrivalTime" id="210"/>
     <uInt64 name="Position" id="206"/>
     <byteVector name="Payload" id="204"/>
   </template>

   <template name="Disconnect" id="203">
     <uInt64 name="ConnectionId" id="201"/>
   </template>

   <template name="Upgrade" id="307">
     <uInt64 name="ConnectionId" id="201"/>
     <uInt32 name="NewRoute" id="205"/>
   </template>

   <template name="Begin" id="208">
     <int32 name="SequnceNo" id="209"/>
   </template>

</templates>
```

***

### NetResponseSchema
Defines a typical networked response. Includes payload, context, connection, host, port, and more.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates>

   <template name="Response" id="101">
     <uInt64 name="ConnectionId" id="1"/>
     <uInt32 name="ContextFlags" id="5"/>
     <byteVector name="Payload" id="3"/>
   </template>

   <template name="Continuation" id="102">
     <uInt64 name="ConnectionId" id="1"/>
     <uInt32 name="ContextFlags" id="5"/>
     <byteVector name="Payload" id="3"/>
   </template>

   <template name="Closed" id="10">
     <string name="Host" id="4" charset="unicode"/>
     <uInt32 name="Port" id="5"/>
   </template>

</templates>
```

***

### RawDataSchema
Defines raw binary data. Basic building block if no other schema is available.

***

### ReleaseSchema
Defines release messages. These are acknowledgments to be sent back to another stage to let them know<br/>that a pipe is free or a task has finished. Use Position and SequenceNo fields to indicate where operation<br/>has ceased.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="Release" id="100">
     <uInt64 name="ConnectionID" id="1"/>
     <uInt64 name="Position" id="2"/>
   </template>

   <template name="ReleaseWithSeq" id="101">
     <uInt64 name="ConnectionID" id="1"/>
     <uInt64 name="Position" id="2"/>
     <int32 name="SequenceNo" id="3"/>
   </template>

</templates>
```

***

### SequentialCtlSchema
Defines sequential control behavior.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="Replay" id="1">
   </template>

   <template name="Clear" id="2">
   </template>

   <template name="MetaRequest" id="3">
   </template>

   <template name="IdToSave" id="4">
    	<uInt64 name="Id" id="10"/>
   </template>

</templates>
```

***

### ServerConnectionSchema
Defines how a connection to a server is established.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="ServerConnection" id="100">

        <uInt64 name="ConnectionGroup"   id="1">
            <copy/>
        </uInt64>
        <uInt64 name="ChannelId"   id="2">
            <delta/>
        </uInt64>

   </template>

</templates>
```

***

### ServerResponseSchema
Defines how a server response is formatted, including channel information, payload,<br/>request , subscriptions, sequence, and more.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

   <template name="ToChannel" id="100">
        <uInt64 name="ChannelId"   id="21">
            <delta/>
        </uInt64>

        <int32 name="SequenceNo" id="23">
        </int32>

		<byteVector name="Payload" id="25"/>
        <uInt32 name="RequestContext" id="24"> <!-- this field must be last -->
        	<!-- low
        	           20 upgrade target

        	     	    1 close connection bit
        	     	    1 upgrade pipe bit
        	  -->
        </uInt32>
   </template>

   <template name="ToSubscription" id="200">

        <uInt64 name="SubscriptionId"   id="22">
            <delta/>
        </uInt64>

        <int32 name="SequenceNo" id="23"><!-- applicable to subscription only -->
        </int32>

        <byteVector name="Payload" id="25"/>
        <uInt32 name="RequestContext" id="24"> <!-- this field must be last -->
        	<!-- low
        	           20 upgrade target

        	     	    1 close connection bit
        	     	    1 upgrade pipe bit
        	  -->
        </uInt32>
   </template>

   <template name="Skip" id="300">
		<byteVector name="Payload" id="25"/>
   </template>

</templates>
```

***

### TestDataSchema
Writes int to specified pipe as a test.

***

## JPG-Raster

### JPGSchema
Defines a JPG image. This includes headers, filename, MCUs, quantization tables, color components,
<br/>and more.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates xmlns="http://www.fixprotocol.org/ns/fast/td/1.1">

	<template name="HeaderMessage" id="1">
		<int32 name="height" id="101"/>
		<int32 name="width" id="201"/>
		<string name="filename" id="301"/>
		<int32 name="final" id="401"/>
	</template>

	<template name="ColorComponentMessage" id="2">
		<int32 name="componentID" id="102"/>
		<int32 name="horizontalSamplingFactor" id="202"/>
		<int32 name="verticalSamplingFactor" id="302"/>
		<int32 name="quantizationTableID" id="402"/>
	</template>

	<template name="QuantizationTableMessage" id="3">
		<int32 name="tableId" id="103"/>
		<int32 name="precision" id="203"/>
		<byteVector name="table" id="303"/>
	</template>

	<template name="MCUMessage" id="4">
		<byteVector name="y" id="104"/>
		<byteVector name="cb" id="204"/>
		<byteVector name="cr" id="304"/>
	</template>

</templates>
```

***
