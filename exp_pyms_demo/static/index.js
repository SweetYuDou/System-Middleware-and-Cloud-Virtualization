function onReady(callback) {
    var intervalId = window.setInterval(function () {
        if (document.getElementsByTagName('body')[0] !== undefined) {
            window.clearInterval(intervalId);
            callback.call(this);
        }
    }, 1000);
}

function setVisible(selector, visible) {
    document.querySelector(selector).style.display = visible ? 'block' : 'none';
}

onReady(function () {
    setVisible('.page', true);
    setVisible('#loading', false);
});

let device = new XMLHttpRequest();
device.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        res = JSON.parse(this.responseText)

        if (!res.status) {
            let table = document.getElementById("devicesInformation");
            for (let rowData of res) {

                let row = document.createElement("tr");
                table.appendChild(row);
                let col0 = document.createElement("td")
                col0.innerText = rowData["device_id"]
                row.appendChild(col0)
                let col1 = document.createElement("td")
                col1.innerText = rowData["name"]
                row.appendChild(col1)
                let col2 = document.createElement("td")
                col2.innerText = rowData["type"]
                row.appendChild(col2)
                let col3 = document.createElement("td")
                col3.innerText = rowData["hardware_model"]
                row.appendChild(col3)
                let col4 = document.createElement("td")
                col4.innerText = rowData["hardware_sn"]
                row.appendChild(col4)
                let col5 = document.createElement("td")
                col5.innerText = rowData["software_version"]
                row.appendChild(col5)
                let col6 = document.createElement("td")
                col6.innerText = rowData["software_last_update"]
                row.appendChild(col6)
                let col7 = document.createElement("td")
                col7.innerText = rowData["network_type"]
                row.appendChild(col7)
                let col8 = document.createElement("td")
                col8.innerText = rowData["network_mac"]
                row.appendChild(col8)
                let col9 = document.createElement("td")
                col9.innerText = rowData["network_ipv4"]
                row.appendChild(col9)
                let col10 = document.createElement("td")
                col10.innerText = rowData["state"]
                row.appendChild(col10)
            }
        } else {
            document.getElementById("devicesInformation").remove();
            let para = document.createElement("p");
            para.innerText = res["status"];
            document.getElementById("page").appendChild(para);
        }
    } else if (this.status === 404) {
        document.getElementById("devicesInformation").remove();
    }
};
url = "/v1/devices"
device.open("GET", url, true);
device.send();

// document.getElementById("loading").style.display="none"

