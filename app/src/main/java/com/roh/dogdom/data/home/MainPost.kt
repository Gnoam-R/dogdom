package com.roh.dogdom.data.home

import com.roh.dogdom.R

class MainPost () {

    public fun getItemViewType(): ArrayList<ItemType> {
        return arrayListOf<ItemType>(
            ItemType.IMAGE,
            ItemType.IMAGES,
            ItemType.IMAGE,
            ItemType.IMAGES,
            ItemType.VIDEO,
            ItemType.VIDEO,
        )
    }

    public fun getImageMembers(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.iv_card_2,
            R.drawable.iv_card_3,
            R.drawable.iv_card_4,
            R.drawable.iv_card_5,
            R.drawable.iv_card_6,
            R.drawable.iv_card_7,
            )
    }

    public fun getImagesMembers(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.iv_images_card1,
            R.drawable.iv_images_card2,
            R.drawable.iv_images_card3,
            R.drawable.iv_images_card4,
            R.drawable.iv_images_card5,
            R.drawable.iv_images_card6,
        )
    }

    public fun getProfileMembers(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.iv_profile_1,
            R.drawable.iv_profile_1,
            R.drawable.iv_profile_1,
            R.drawable.iv_profile_1,
            R.drawable.iv_profile_1,
            R.drawable.iv_profile_1,
        )
    }

    public fun getNameMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "Mirabelle Swift",
            "William Watts",
            "Jorge Long",
            "Huntington",
            "Quentin Raman",
            "Edgar",

        )
    }

    public fun getCommentMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "Adwords Keyword Research For Beginners.",
            "How To Boost Your Traffic Of Your Blog And Destroy The Competition.\uD83D\uDE0F \uD83D\uDE0F \uD83D\uDE0B",
            "A dog is a beloved, intelligent, and very loyal animal. So I like dogs very much.",
            "With golden retriever together of the day is always short, soon to the New Year, leave you in the city.",
            "Your dog is only a part of your world, but to your dog, you are the world.\uD83D\uDE0A \uD83D\uDE0A",
            "If you just because of its cute appearance, silly expression so love, please do not keep it.",
        )
    }

    public fun getLikeMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "5,233",
            "9,236",
            "9,784",
            "8,668",
            "1,688",
            "668",


        )
    }

    public fun getMessageMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "189",
            "639",
            "589",
            "168",
            "685",
            "452",
            )
    }

    public fun getShareMembers(): ArrayList<String> {
        return arrayListOf<String>(
            "238",
            "173",
            "168",
            "356",
            "5233",
            "828",
        )
    }
}