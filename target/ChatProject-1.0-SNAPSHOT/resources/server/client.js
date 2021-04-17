// const connection = new WebSocket("ws://localhost:8989");
// const button = document.querySelector("#send");
//
// connection.onopen = (event) => {
//     console.log("WebSocket is open now.");
// };
//
// connection.onclose = (event) => {
//     console.log("WebSocket is closed now.");
// };
//
// connection.addEventListener("open", () =>{
//     console.log("We are connected");
// })
//
// connection.onerror = (event) => {
//     console.error("WebSocket error observed:", event);
// };
//
// connection.onmessage = (event) => {
//     // append received message from the server to the DOM element
//     const chat = document.querySelector(".chat_message_box");
//     chat.innerHTML += event.data;
// };
//
// button.addEventListener("click", () => {
//     const name = document.querySelector("#name");
//     const message = document.querySelector("#message");
//     const data = `<p>${name.value}: ${message.value}</p>`;
//
//     connection.send(data);
//
//     name.value = "";
//     message.value = ""
// });