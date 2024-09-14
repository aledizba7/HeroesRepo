package com.example.heroesapp.models

data class Publisher(val id: Int,val name:String,val image:String){
    companion object{
        val publishers = mutableListOf<Publisher>(
            Publisher(1,"Marvel","https://lovingseries.com/wp-content/uploads/2014/10/marvel-logo-wallpaper.jpg"),
            Publisher(2,"DC","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/2f45fc20181821.562e702441772.jpg"),
            Publisher(3,"Dark Horse","https://variety.com/wp-content/uploads/2015/07/dark-horse-comics-logo.jpg?w=681&h=383&crop=1"),
            Publisher(4,"IDW","https://static.wikia.nocookie.net/transformers/images/5/52/IDW_Logo.png/revision/latest?cb=20230610185915&path-prefix=es"),
            Publisher(5,"Top Cow","https://static1.cbrimages.com/wordpress/wp-content/uploads/2018/05/top-cow-logo.jpg"),
        )
    }
}