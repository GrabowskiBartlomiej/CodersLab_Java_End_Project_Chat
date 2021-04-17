const WebSocket = require("ws");

const wss = new WebSocket.Server({port: 8082});

wss.on('connection', (ws) => {
    console.log("it is listening")

    ws.on('message', (data) => {

        wss.clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(data);
            }
        });
    });
});