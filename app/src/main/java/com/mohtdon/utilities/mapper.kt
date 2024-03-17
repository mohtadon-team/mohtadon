package com.mohtdon.utilities

import com.mohtdon.domain.models.radio.RadioEntity
import com.mohtdon.domain.models.reciters.MoshafEnitity
import com.mohtdon.player.AudioItem


fun moshafEntityToAudioItemList(moshafEnitity: MoshafEnitity) :List<AudioItem> {

    val listOfAudioItem: ArrayList<AudioItem> = ArrayList()

     moshafEnitity.surah_list.split(',').map {
         listOfAudioItem.add(
             AudioItem(
                 reciterAndHisMoshaf = moshafEnitity.reciterName.plus(" - ${moshafEnitity.moshafName}"),
                 surah = suraMap[it]?.name ?: "",
                 image = "https://png.pngtree.com/png-clipart/20210323/ourmid/pngtree-holy-quran-with-navy-cover-png-image_3131452.jpg",
                 source = moshafEnitity.server.plus(suraMap[it]?.originalNumber).plus(".mp3")
             )
         )

    }
   return  listOfAudioItem
}

fun radioEntityToAudioItemList(radioEntity: List<RadioEntity>) :List<AudioItem> {

    val listOfAudioItem: ArrayList<AudioItem> = ArrayList()

    radioEntity.forEach{

        listOfAudioItem.add( AudioItem(
            reciterAndHisMoshaf = it.name,

            surah =  "مباشر",
            image = "",
            source = it.source
        ) )

    }
    return  listOfAudioItem
}

