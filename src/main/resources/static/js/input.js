window.onload = function () {
    let book3 = new Book("bookImage/img_2.png","DescriptionBook/SqlDescription.png","book3")
    let book1 = new Book("bookImage/fon.png","DescriptionBook/SqlDescription.png","book1")
    let book2 = new Book("bookImage/img_1.png","DescriptionBook/SqlDescription.png","book2")
    book1.createBook()
    book2.createBook()
    book3.createBook()
}

function Book(namePhoto, nameDescription, id) {
    this.namePhoto = namePhoto
    this.nameDescription = nameDescription
    this.id = id
}
Book.prototype.createBook = function () {
    console.log(this.nameDescription)
    let a = document.getElementById(this.id)
    let descr = this.nameDescription
    let adressPhoto = this.namePhoto

    // this === instance of Book
    let self = this;
    let that = this;

    a.onmouseover = (function (n) {
        //this === a;
        n.target.src = self.nameDescription;
    }).bind(this);//почему не работает если передавать через this

    a.onmouseout = function (n) {
        console.log()
        n.target.src = that.namePhoto
    }
    a.onclick = function (n) {
        n.target.src = "img_1.png"
        setTimeout(()=>{n.target.src = "fon.png"},10000)
//еще не готова
    }
}

onmouseover.call(a);