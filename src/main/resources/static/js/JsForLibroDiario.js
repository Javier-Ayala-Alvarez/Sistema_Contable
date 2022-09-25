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

function sendFormModal(){
    document.formModal.submit();
}