const d = document,
  $table = d.querySelector(".crud-table"),
  $form = d.querySelector(".crud-form"),
  $title = d.querySelector(".crud-title"),
  $template = d.getElementById("crud-template").content,
  $fragment = d.createDocumentFragment();

console.dir(d.getElementById("crud-template"));
const getAll = async () => {
  try {
    let res = await fetch("http://localhost/books"),
      json = await res.json();

    if (!res.ok) throw { status: res.status, statusText: res.statusText };

    console.log(json);
    json.forEach((el) => {
      $template.querySelector(".titulo").textContent = el.bookTitle;
      $template.querySelector(".volumen").textContent = el.bookVolume;
      $template.querySelector(".position").textContent = el.location.position;
      $template.querySelector(".sala").textContent = el.location.room;
      $template.querySelector(".pasillo").textContent = el.location.hallway;
      $template.querySelector(".librero").textContent = el.location.bookcase;
      $template.querySelector(".edit").dataset.id = el.id;
      $template.querySelector(".edit").dataset.titulo = el.bookTitle;
      $template.querySelector(".edit").dataset.volumen = el.bookVolume;
      $template.querySelector(".edit").dataset.position = el.location.position;
      $template.querySelector(".edit").dataset.sala = el.location.room;
      $template.querySelector(".edit").dataset.pasillo = el.location.hallway;
      $template.querySelector(".edit").dataset.librero = el.location.bookcase;
      $template.querySelector(".delete").dataset.id = el.id;
      $template.querySelector(".delete").dataset.titulo = el.bookTitle;

      let $clone = d.importNode($template, true);
      $fragment.appendChild($clone);
    });

    $table.querySelector("tbody").appendChild($fragment);
  } catch (err) {
    let message = err.statusText || "Ocurrió un error";
    $table.insertAdjacentHTML(
      "afterend",
      `<p><b>Error ${err.status}: ${message}</b></p>`
    );
  }
};

d.addEventListener("DOMContentLoaded", getAll);

d.addEventListener("submit", async (e) => {
  if (e.target === $form) {
    e.preventDefault();
    console.log("post");
    if (!e.target.id.value) {
      //Create - POST
      try {
        let options = {
            method: "POST",
            headers: {
              "Content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify({
              bookTitle: e.target.bookTitle.value,
              bookVolume: e.target.bookVolume.value,
              location: {
                room: e.target.room.value,
                hallway: e.target.hallway.value,
                bookcase: e.target.bookcase.value,
                position: e.target.position.value,
              },
            }),
          },
          res = await fetch("http://localhost/addbook", options),
          json = await res.json();
        console.log(json);

        if (!res.status.ok) 
          throw { status: res.status, statusText: res.statusText };

        location.reload();
      } catch (err) {
        let message = err.statusText || "Ocurrió un error";
        $form.insertAdjacentHTML(
          "afterend",
          `<p><b>Error ${err.status}: ${message}</b></p>`
        );
      }
    } else {
      //Update - PUT
      try {
        let options = {
            method: "PUT",
            headers: {
              "Content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify({
              id: e.target.id.value,
              bookTitle: e.target.bookTitle.value,
              bookVolume: e.target.bookVolume.value,
              location: {
                id: e.target.id.value,
                room: e.target.room.value,
                hallway: e.target.hallway.value,
                bookcase: e.target.bookcase.value,
                position: e.target.position.value,
              },
            }),
          },
          res = await fetch(`http://localhost/updatebook`, options),
          json = await res.json();

        if (!res.ok) throw { status: res.status, statusText: res.statusText };

        location.reload();
      } catch (err) {
        let message = err.statusText || "Ocurrió un error";
        $form.insertAdjacentHTML(
          "afterend",
          `<p><b>Error ${err.status}: ${message}</b></p>`
        );
      }
    }
  }
});

d.addEventListener("click", async (e) => {
  if (e.target.matches(".edit")) {
    $title.textContent = "Editar Libro";
    $form.bookTitle.value = e.target.dataset.titulo;
    $form.bookVolume.value = e.target.dataset.volumen;
    $form.room.value = e.target.dataset.sala;
    $form.hallway.value = e.target.dataset.pasillo;
    $form.bookcase.value = e.target.dataset.librero;
    $form.position.value = e.target.dataset.position;
    $form.id.value = e.target.dataset.id;
  }

  if (e.target.matches(".delete")) {
    let isDelete = confirm(
      `¿Estás seguro de eliminar el libro ${e.target.dataset.titulo}?`
    );

    if (isDelete) {
      //Delete - DELETE
      try {
        let options = {
            method: "DELETE",
            headers: {
              "Content-type": "application/json; charset=utf-8",
            },
          },
          res = await fetch(
            `http://localhost/deletebook/${e.target.dataset.id}`,
            options
          ),
          json = await res.json();

        if (!res.ok) throw { status: res.status, statusText: res.statusText };

        location.reload();
      } catch (err) {
        let message = err.statusText || "Ocurrió un error";
        alert(`Error ${err.status}: ${message}`);
      }
    }
  }
});
