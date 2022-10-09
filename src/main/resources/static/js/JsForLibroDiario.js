

const tablaLibroDiario = document.getElementById("tablaLibroDiario");
tablaLibroDiario.addEventListener("click" , e =>{
    console.log(e.target.tagName)
    if (e.target && e.target.tagName === "A" &&( e.target.getAttribute("id")) ==="btnRegistroModal" ) {
        let url = "/LibroDiario/detalle/"+ e.target.getAttribute("value");

        getData(url, null).then((response) => response.text())
            .then(text => {

                document.getElementById("detallePartida").innerHTML = text;
                document.getElementById("openModalRegistro").click();
            })
            .catch(function (err) {
                console.log(err);
                alert("a ocurrido un errror"); // si ocurrio un error
            });

    }
});


function getModal() {
    let url = "LibroDiario/modal";

    getData(url, null).then((response) => response.text())
        .then(text => {

            document.getElementById("getPartida").innerHTML = text;
            document.getElementById("openModal").click();
        })
        .catch(function (err) {
            console.log(err);
            alert("a ocurrido un errror"); // si ocurrio un error
        });

}

function sendFormModal() {
    document.formModal.submit();
}




