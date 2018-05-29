---
title: "Stages"
permalink: /catalog/list-stages
toc: true
---
Stages will be listed here.

### AbstractAppendablePayloadResponseStage
> Abstraction for response payloads using UTF-8 or other text encoding.
See ByteArrayPayloadResponseStage to implement a custom REST responder that responds
only using bytes.
Use this to define custom HTTP REST behavior.

<table>
    <tr>
        <th>Input</th>
        <td>
           Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs:
            <em>HTTP schema request inputs</em>
            <br/><br/>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <td>
           Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs:
           <em>Response schema outputs</em>
           <br/><br/>
        </td>
    </tr>
</table>

<table>
<tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Input request</em><br/><br/>Pipe&lt;<a href="list-schemas#?"><code>?</code></a>&gt; otherInputs: <em>Other inputs</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Output response</em><br/><br/></td></tr>
</table>

### ByteArrayPayloadResponseStage
> Extend this class to write your own REST responders. Implement payload()
to return a byte array. See AbstractAppendablePayloadResponseStage for implementing
a UTF-8 REST responder.



<table>
<tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Input pipes with HTTPRequestSchema</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Output pipes as several ServerResponseSchema</em><br/><br/></td></tr>
</table>

<table>
<tr><th>Input</th><td>Pipe&lt;<a href="list-schemas#httprequestschema"><code>HTTPRequestSchema</code></a>&gt; inputs: <em>Input pipes with HTTPRequestSchema</em><br/><br/>Pipe otherInputs: <em>Multiple other input pipes to respond to</em><br/><br/></td></tr><tr><th>Output</th><td>Pipe&lt;<a href="list-schemas#serverresponseschema"><code>ServerResponseSchema</code></a>&gt; outputs: <em>Output pipes as several ServerResponseSchema</em><br/><br/></td></tr>
</table>