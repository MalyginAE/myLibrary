window.onload = function () {
    let man = document.getElementById("image")
    man.onmouseover = (x) => {
        x.target.src = "/images/sign_up/reg.png";
    }
    man.onmouseout = (x) => {
        x.target.src = "/images/sign_up/6.png";
    }
    man.onclick = x => {
        x.target.href  = "/images/sign_up/6.png";
    }
}


// Book.prototype.createBook = function () {
//     console.log(this.nameDescription)
//     let a = document.getElementById(this.id)
//     let descr = this.nameDescription
//     let adressPhoto = this.namePhoto
//
//     // this === instance of Book
//     let self = this;
//     let that = this;
//
//     a.onmouseover = (function (n) {
//         //this === a;
//         n.target.src = self.nameDescription;
//     }).bind(this);//почему не работает если передавать через this
//
//     a.onmouseout = function (n) {
//         console.log()
//         n.target.src = that.namePhoto
//     }
//     a.onclick = function (n) {
//         n.target.src = "img_1.png"
//         setTimeout(()=>{n.target.src = "fon.png"},10000)
// //еще не готова
//     }
// }
//
// onmouseover.call(a);