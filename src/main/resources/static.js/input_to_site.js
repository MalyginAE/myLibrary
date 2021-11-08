window.onload = function () {
    //////////////////////
    let img = document.getElementById("image");
    img.onclick = x => {
        let image = x.target;
        image.src = "image/smile.png";
        setTimeout(function () {
            document.getElementById("image").src = "image/face/6.png";
            //document.getElementById("image").getAttribute() = "image/face/6.png";
        },3000)
    }
    ///////////////////////////////
    let password = document.getElementById("password");
    let strPassword = [];
    let findPassword = passwordNear();
    ////////////////////////////////////
    password.onkeydown = x => {
        //console.log(x.key)
        //console.log(String.fromCharCode(x.keyCode))
        let isBS = isBackSpace(x.key, strPassword);
        if (isBS())
            strPassword[strPassword.length] = x.key;
        let img = document.getElementById("image")
        img.src = findPassword(strPassword.join(""))
        //console.log(findPassword(strPassword.join("")))
    }
////////////////////////////////////////////////////

}

function isBackSpace(char, array) {
    function isbackDel() {
        if (char === "Backspace") {
            if (array.length > 0) array.length--;

            return false;
        }
        return true;
    }

    return isbackDel;
}

function User(imageId, password) {
    this.imageId = imageId;
    this.password = password;

    this.getPhotoIdJPG = function (){
        return "image/face/"+this.imageId + ".png"
    }
}

function Users() {
    function createUsers() {
        let Anatoly = new User(1, "1617");
        let Andrey = new User(2, "qwerty");
        let Maria = new User(3, "i love andrey");
        let Nikita = new User(4, "algorithm");
        let Nekit = new User(5, "i am strong");
        let Stranger = new User(6);
        return [Anatoly, Andrey, Maria, Nikita, Nekit, Stranger]
    }

    return createUsers
}

function passwordNear() {
    let users = Users()()
    function passwordNearToTruth(password) {
        //console.log(password)
        for (let i = 0; i < users.length; i++) {
            //console.log(users[i].password)
            if (users[i].password != undefined && password!="")
                if (users[i].password.includes(password))
                    //console.log(users[i].password)
                    return users[i].getPhotoIdJPG()

        }return "image/face/"+6+".png"
    }
    return passwordNearToTruth
}
