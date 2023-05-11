package com.roh.dogdom.data.message

import com.roh.dogdom.R

class MessageVerticalPost () {
    public fun getImageMembers(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.iv_dog1,
            R.drawable.iv_alarm,
            R.drawable.iv_dogdom,
            )
    }

    public fun getTitleMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "Love dog little assistant",
            "The system informs",
            "Dogdom",

        )
    }

    public fun getDescriptionMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "Congratulations! Your article was recommended by dog lovers to feature",
            "We will spare no effort to protect the ecology of the community and severely crack down on violations of regulations",
            "Your March report has been generated, click to view.",
        )
    }

    public fun getDateMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "02/07",
            "05/18",
            "11 days ago",
        )
    }

}