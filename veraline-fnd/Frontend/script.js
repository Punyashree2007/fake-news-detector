function detectNews() {
    const title = document.getElementById("news").value;
    const content = "Some content here";
    const source = document.getElementById("source").value;

    fetch("http://localhost:5000/detect", {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: `${title}|${content}|${source}`
    })
    .then(response => response.text())
    .then(result => {
        document.getElementById("result").innerText = result;
    })
    .catch(err => console.error(err));
}