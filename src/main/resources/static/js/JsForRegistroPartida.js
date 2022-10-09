const tablaRegistroPartida = document.getElementById("tablaRegistroPartida");
tablaRegistroPartida.addEventListener("click", e => {

    if (e.target && e.target.tagName === "BUTTON" && (e.target.getAttribute("name") === "deleteRegistro")) {
        let id = e.target.getAttribute("value");

        const url = "/LibroDiario/registroPartida/delete/" + id;
        const data = null;
        getData(url, data).then((response) => {

            if (!response.ok) {
                alert("A ocurrido un error vuelva a intentarlo");
            }
            window.location.href = "/LibroDiario/registroPartida"
        })
            .catch(function (err) {
                console.log(err);
                alert("a ocurrido un errror"); // si ocurrio un error
            });

    }

});


const formPartida = document.getElementById("formPartida");
formPartida.addEventListener("submit", e => {
    e.preventDefault();
    let debe = document.getElementById("idDebe").value;
    let haber = document.getElementById("idHaber").value;
    if (debe != haber) {
        alert("Verifique que debe sea igual a haber");
        return;
    }
    document.formPartida.submit();
});

function getCuentas() {
    //dataCuentas
    const input = document.getElementById("numeroCuenta");
    const url = "/Catalogo/buscar/" + input.value;
    const data = null;
    getData(url, data).then((response) => response.text())
        .then(text => {
            let input = document.getElementById("numeroCuenta");
            input.innerHTML = text;
            input.setAttribute("list", "dataCuentas")

        })
        .catch(function (err) {
            console.log(err);
            alert("a ocurrido un errror"); // si ocurrio un error
        });

}