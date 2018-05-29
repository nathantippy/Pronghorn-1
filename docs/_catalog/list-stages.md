---
title: "Stages"
permalink: /catalog/list-stages
toc: true
---
## Pronghorn

### AbstractAppendablePayloadResponseStage
> Abstraction for response payloads using UTF-8 or other text encoding.
See ByteArrayPayloadResponseStage to implement a custom REST responder that responds
only using bytes.
Use this to define custom HTTP REST behavior.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>HTTP schema request inputs</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Response schema outputs</em><br/><br/></td></tr></table><table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Input request</em><br/><br/>Pipe&lt;<a href="list-schemas#?"><code>?</code></a>&gt; otherInputs: <em>Other inputs</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Output response</em><br/><br/></td></tr></table>

***

### BlockingSupportStage
> Stage that allows for blocking calls, e.g. to make a call to a database and then wait
until a response is received.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; input: <em>Input that will be released until ready</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#p"><code>P</code></a>&gt; output: <em>Pipe onto which the input will be released on</em><br/><br/></td></tr></table>

***

### ByteArrayEqualsStage
> Takes an array and an input pipe.
If the bytes match the raw bytes, wasEqual will be true.
For testing RawDataSchema.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; input: <em>Pipe with RawDataSchema that will compares to the byte array</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### ByteArrayPayloadResponseStage
> Extend this class to write your own REST responders. Implement payload()
to return a byte array. See AbstractAppendablePayloadResponseStage for implementing
a UTF-8 REST responder.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Input pipes with HTTPRequestSchema</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Output pipes as several ServerResponseSchema</em><br/><br/></td></tr></table><table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Input pipes with HTTPRequestSchema</em><br/><br/>Pipe otherInputs: <em>Multiple other input pipes to respond to</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Output pipes as several ServerResponseSchema</em><br/><br/></td></tr></table>

***

### ByteArrayProducerStage
> Takes an array of bytes and writes them to the output pipe once.
Useful for testing RawDataSchema

<table><tr><th>Input</th><td><em>No input pipes</em><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; output: <em>The pipe onto which the rawData byte array will be written to</em><br/><br/></td></tr></table>

***

### ClientSocketReaderStage
> Client-side stage that reads sockets using a ClientCoordinator
based on a release acknowledgment.
Accepts only expected calls (unlike ServerSocketReaderStage), since
it is a client.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#releaseschema"><code>ReleaseSchema</code></a>&gt; parseAck: <em>The release acknowledgment input pipes.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe output: <em>The read payload from the socket.</em><br/><br/></td></tr></table>

***

### ClientSocketWriterStage
> Write to a socket using a client coordinator.

<table><tr><th>Input</th><td>Pipe input: <em>Payload that will be written to socket.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### ConsoleJSONDumpStage
> For some Schema T encode this data in JSON and write it to the target appendable.
Can be set to assume that bytes are UTF8.
The default output is System.out

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; input: <em>Pipe to be dumped</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### ConsoleSummaryStage
> For some Schema T keeps running totals of each message type.
Periodically reports the Number of each message type to target appendable.
Default target is system.out.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; input: <em>Any schema input pipe that should be summarized.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### DotModuleStage
> Rest service stage which responds with the dot file needed to display the telemetry.
This dot file is a snapshot of the system representing its state within the last 40 ms.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Pipe containing request for generated .dot file.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Pipe that will contain HTTP response containing newly generated .dot file.</em><br/><br/></td></tr></table>

***

### DummyRestStage
> Dummy REST stage that simply returns <strong>501</strong>. Use this to quickly build yourself a web server without
worrying about implementation, or to test concurrent behavior.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputPipes: <em>Input pipes containing the HTTP request</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>No output except 501 error.</em><br/><br/></td></tr></table>

***

### FileBlobReadStage
> Reads files from disk as blobs based on an input path.

<table><tr><th>Input</th><td><em>No input pipes</em><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; output: <em>Pipe that file from inputPathString will be written to.</em><br/><br/></td></tr></table>

***

### FileBlobWriteStage
> Writes data to file on disk (in blobs).

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; input: <em>RawDataSchema that will be written  to file.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### FileReadModuleStage
> Minimal memory usage and leverages SSD for file reading module.
Provides HTTP file transfer, i.e. browser requests file and this stage returns it.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Array of requests for file(s).</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Array of responses with the requested file(s).</em><br/><br/></td></tr></table>

***

### HTTP1xResponseParserStage
> Parses HTTP1.x responses from the server and sends an acknowledgment to an output pipe
to be sent back to a request stage.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; input: <em>Pipe containing the HTTP payload.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#netresponseschema"><code>NetResponseSchema</code></a>&gt; output: <em> Net response.</em><br/><br/>Pipe&lt;<a href="list-schemas#releaseschema"><code>ReleaseSchema</code></a>&gt; ackStop: <em>Acknowledgment for forwarding.</em><br/><br/></td></tr></table>

***

### HTTP1xRouterStage
> Main HTTP router. Quickly redirects any incoming traffic to corresponding routes.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; input: <em>The payload that will be routed.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; outputs: <em>The HTTP request parsed from the NetPayloadSchema.</em><br/><br/>Pipe&lt;<a href="list-schemas#httplogrequestschema"><code>HTTPLogRequestSchema</code></a>&gt; log: <em>Logging output.</em><br/><br/>Pipe&lt;<a href="list-schemas#releaseschema"><code>ReleaseSchema</code></a>&gt; ackStop: <em>Acknowledgment for ReleaseSchema.</em><br/><br/></td></tr></table>

***

### HTTPClientRequestStage
> Takes a HTTP client request and responds with a net payload using a TrieParserReader.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#clienthttprequestschema"><code>ClientHTTPRequestSchema</code></a>&gt; input: <em>Multiple HTTP client requests</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe output: <em>Multiple net payload responses</em><br/><br/></td></tr></table>

***

### HTTPLogUnificationStage
> Takes multiple HTTP log requests and responses and turns them into a RawDataSchema for
easier output.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httplogrequestschema"><code>HTTPLogRequestSchema</code></a>&gt; requestInputs: <em>All HTTP request logs.</em><br/><br/>Pipe&lt;<a href="list-schemas#httplogresponseschema"><code>HTTPLogResponseSchema</code></a>&gt; responseInputs: <em>All HTTP response logs.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; output: <em>All the request and response logs combined onto the output pipe as a RawDataSchema.</em><br/><br/></td></tr></table>

***

### HTTPRequestJSONExtractionStage
> Using a JSONExtractor, takes a HTTP request with JSON and turns it into
a ServerResponseSchema onto a ServerResponseSchema pipe.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; input: <em>The HTTP request containing JSON.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; output: <em>The HTTP response.</em><br/><br/>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; err: <em>Contains ServerResponseSchema if error occurred</em><br/><br/></td></tr></table>

***

### NetResponseDumpStage
> Dumps a NetResponseSchema onto an Appendable target.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netresponseschema"><code>NetResponseSchema</code></a>&gt; input: <em>The net response input pipe.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### NetResponseJSONStage
> Parses a JSON response using a JSONStreamVisitor.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netresponseschema"><code>NetResponseSchema</code></a>&gt; input: <em>The NetResponseSchema containing the JSON to be parsed.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table><table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netresponseschema"><code>NetResponseSchema</code></a>&gt; input: <em>The NetResponseSchema containing the JSON to be parsed.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#m"><code>M</code></a>&gt; output: <em>Put the parsed JSON onto this output pipe.</em><br/><br/>Pipe otherOutputs: <em>Put the parsed JSON onto multiple output pipes.</em><br/><br/></td></tr></table>

***

### OrderSupervisorStage
> Consumes the sequence number in order and holds a pool entry for this connection.
Sends the data in order to the right pool entry for encryption to be applied down stream.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; inputPipes: <em>The server response which will be supervised.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#httplogresponseschema"><code>HTTPLogResponseSchema</code></a>&gt; log: <em>The log output pipe.</em><br/><br/>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; outgoingPipes: <em>The net payload after order is enforced.</em><br/><br/></td></tr></table>

***

### PipeCleanerStage
> For some schema T consumes all the data on the input pipes.
The data is not reported anyway.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; input: <em>Any schema input pipe</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table><table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; input: <em>An array of any input pipe</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### PipeMonitorStage
> Stage which monitors pipes in real time.
This data is passed along for the telemetry.

<table><tr><th>Input</th><td><em>No input pipes</em><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#?"><code>?</code></a>&gt; observedRingBuffer: <em>observation pipes</em><br/><br/>Pipe notifyRingBuffer: <em>notify pipes</em><br/><br/></td></tr></table>

***

### PipeNoOp
> Empty stage which does no work.  It takes an output pipe and writes nothing.

<table><tr><th>Input</th><td><em>No input pipes</em><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; output: <em>Does nothing.</em><br/><br/></td></tr></table>

***

### ReplicatorStage
> Given n ring buffers with the same FROM/Schema
Does not require schema knowledge for copy but does ensure targets and source have the same FROM.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; source: <em>Any input pipe that will be replicated.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; a: <em>Target pipe; will be joined with b.</em><br/><br/>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; b: <em>Target pipe; will be joined with a.</em><br/><br/></td></tr></table><table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; source: <em>Any input pipe that will be replicated.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; targets: <em>Multiple targets to which the source pipe is replicated.</em><br/><br/></td></tr></table>

***

### ResourceModuleStage
> Fetches resources as HTTP responses based on request.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Multiple HTTPRequest that are requesting resource(s).</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Responds with the resource(s) if it/they exists.</em><br/><br/></td></tr></table>

***

### RoundRobinRouteStage
> For some schema T distributes the records across N output streams of the same

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; input: <em>Schema T input pipes that will be distributed</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; outputs: <em>Pipes on which the input pipe will be distributed on</em><br/><br/></td></tr></table>

***

### SSLEngineUnWrapStage
> Unwraps encrypted content for HTTPS/SSL.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; encryptedContent: <em>Encrypted content to be unencrypted.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; outgoingPipeLines: <em>Unencrypted content.</em><br/><br/>Pipe&lt;<a href="list-schemas#releaseschema"><code>ReleaseSchema</code></a>&gt; relesePipe: <em>Acknowledgment for release.</em><br/><br/>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; handshakePipe: <em>Responds with a handshake.</em><br/><br/></td></tr></table>

***

### SSLEngineWrapStage
> Wraps plain content into encrypted content for HTTPS/SSL.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; plainContent: <em>Plain content payload to be encrypted.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe encryptedContent: <em>Encrypted payload.</em><br/><br/></td></tr></table>

***

### SequentialFileReadWriteStage
> Sequentially read and write to file.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#sequentialctlschema"><code>SequentialCtlSchema</code></a>&gt; control: <em>Schemas defining sequential control.</em><br/><br/>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; input: <em>Data being read.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#sequentialrespschema"><code>SequentialRespSchema</code></a>&gt; response: <em>Schemas defining sequential response.</em><br/><br/>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; output: <em>Data being written.</em><br/><br/></td></tr></table>

***

### SequentialReplayerStage
> Replays a sequential read/write based on store requests and load responses.



***

### ServerNewConnectionStage
> General base class for server construction.
Server should minimize garbage but unlike client may not be possible to remove it.
No protocol specifics are found in this class, only socket usage logic.

<table><tr><th>Input</th><td><em>No input pipes</em><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverconnectionschema"><code>ServerConnectionSchema</code></a>&gt; newClientConnections: <em>The ServerConnectionSchema containing the newest client information.</em><br/><br/></td></tr></table>

***

### ServerSocketReaderStage
> Server-side stage that reads from the socket. Useful for building a server.
Accepts unexpected calls (unlike ClientSocketReaderStage).

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#releaseschema"><code>ReleaseSchema</code></a>&gt; ack: <em>The release acknowledgment.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#netpayloadschema"><code>NetPayloadSchema</code></a>&gt; output: <em>The read payload from the socket.</em><br/><br/></td></tr></table>

***

### ServerSocketWriterStage
> Server-side stage that writes back to the socket. Useful for building a server.

<table><tr><th>Input</th><td>Pipe dataToSend: <em>The data to be written to the socket.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### TapeReadStage
> Reads a Pronghorn "tape" from disk and writes it back onto a pipe.
Tape is a file format that mimics Pronghorn pipe data format, useful for
very structured formats.

<table><tr><th>Input</th><td><em>No input pipes</em><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; output: <em>Writes the read tape directly back onto a RawDataSchema pipe.</em><br/><br/></td></tr></table>

***

### TapeWriteStage
> Write data from a pipe directly to disk. This records both the slab and blob data without any schema concerns.
This is the simplest way to save data in a pipe to disk.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#t"><code>T</code></a>&gt; source: <em>The input pipe that will be written as a tape to disk.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### ToOutputStreamStage
> Takes the RawDataSchema on the input pipe and writes it to an output stream.

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#rawdataschema"><code>RawDataSchema</code></a>&gt; inputRing: <em>The RawDataSchema pipe that will be written to the output stream.</em><br/><br/></td></tr><tr><th>Output</th><td><em>No output pipes</em><br/></td></tr></table>

***

### UpgradeToWebSocketStage
> Go from HTTP-based server directly to web sockets for massive speed improvements.
 <br/><br/>@author Nathan Tippy
 <br/><br/>@see <a href="https://github.com/objectcomputing/Pronghorn">Pronghorn</a>

<table><tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputPipes: <em>HTTP request schema to be upgraded.</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Resulting ServerResponseSchema (multiple) after upgrade.</em><br/><br/></td></tr></table>

***


