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

function getCuentas() {
    //dataCuentas
    const input = document.getElementById("numeroCuenta");
    const url = "/Catalogo/buscar/" + input.value;
    const data = null;
    getData(url, data).then((response) => response.text())
        .then(text => {
            let input = document.getElementById("numeroCuenta");
            input.innerHTML = text;
           input.setAttribute("list","dataCuentas")

        })
        .catch(function (err) {
            console.log(err);
            alert("a ocurrido un errror"); // si ocurrio un error
        });

}
function guardarPartida(e){
    e.preventDefault();
    let debe = document.getElementById("idDebe").value;
    let haber = document.getElementById("idHaber").value;
    if (debe != haber){
        alert("Verifique que debe sea igual a haber");
        return;
    }
    document.formPartida.submit();

}