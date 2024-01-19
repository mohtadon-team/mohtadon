package com.example.mohtdon.ui.compose.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.mohtdon.R
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor1
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor2
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor3
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor4
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor5
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor6


data class TopicItem(val id: Int, val name: String)
data class HadithItem(val id: Int, val text: String)
data class HomeRowItems(
    val id: Int,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val cardColor: Color
)

val hadithTopicsList = listOf(
    TopicItem(1, "الصلاة"),
    TopicItem(2, "الصوم"),
    TopicItem(3, "الزكاة"),
    TopicItem(4, "الرزق"),
    TopicItem(5, "الحزن"),
    TopicItem(6, "معاملات المسلم"),
    TopicItem(7, "الجهاد"),
    TopicItem(8, "أذكار الآذان"),
    TopicItem(9, "أذكار بعد الصلاة"),
    TopicItem(10, "أذكار نزول المطر")
)

val hadithList = listOf(
    HadithItem(
        1,
        "قال رسول الله صلى الله عليه وسلم: (الصَّلَاةُ الخَمْسُ، وَالْجُمْعَةُ إلى الجُمْعَةِ، كَفَّارَةٌ لِما بيْنَهُنَّ، ما لَمْ تُغْشَ الكَبَائِرُ)"
    ),
    HadithItem(
        2,
        "قال رسول الله صلى الله عليه وسلم: (أَرَأَيْتُمْ لو أنَّ نَهْرًا ببَابِ أَحَدِكُمْ يَغْتَسِلُ منه كُلَّ يَومٍ خَمْسَ مَرَّاتٍ، هلْ يَبْقَى مِن دَرَنِهِ شيءٌ؟ قالوا: لا يَبْقَى مِن دَرَنِهِ شيءٌ، قالَ: فَذلكَ مَثَلُ الصَّلَوَاتِ الخَمْسِ، يَمْحُو اللَّهُ بهِنَّ الخَطَايَا)"
    ),
    HadithItem(
        3,
        "قال رسول الله صلى الله عليه وسلم: (ما مِن مُسْلِمٍ يَتَطَهَّرُ، فيُتِمُّ الطُّهُورَ الذي كَتَبَ اللَّهُ عليه، فيُصَلِّي هذِه الصَّلَواتِ الخَمْسَ، إلَّا كانَتْ كَفّاراتٍ لِما بيْنَها)"
    ),
    HadithItem(
        4,
        "قال رسول الله صلى الله عليه وسلم: (لا يَتَوَضَّأُ رَجُلٌ مُسْلِمٌ فيُحْسِنُ الوُضُوءَ فيُصَلِّي صَلاةً إلَّا غَفَرَ اللَّهُ له ما بيْنَهُ وبيْنَ الصَّلاةِ الَّتي تَلِيها)"
    ),
    HadithItem(
        5,
        "قال رسول الله صلى الله عليه وسلم: (لا يَتَوَضَّأُ رَجُلٌ مُسْلِمٌ فيُحْسِنُ الوُضُوءَ فيُصَلِّي صَلاةً إلَّا غَفَرَ اللَّهُ له ما بيْنَهُ وبيْنَ الصَّلاةِ الَّتي تَلِيها)"
    ),
    HadithItem(
        6,
        "قال رسول الله صلى الله عليه وسلم: (لا يَتَوَضَّأُ رَجُلٌ مُسْلِمٌ فيُحْسِنُ الوُضُوءَ فيُصَلِّي صَلاةً إلَّا غَفَرَ اللَّهُ له ما بيْنَهُ وبيْنَ الصَّلاةِ الَّتي تَلِيها)"
    ),
    HadithItem(
        7,
        "قال رسول الله صلى الله عليه وسلم: (لا يَتَوَضَّأُ رَجُلٌ مُسْلِمٌ فيُحْسِنُ الوُضُوءَ فيُصَلِّي صَلاةً إلَّا غَفَرَ اللَّهُ له ما بيْنَهُ وبيْنَ الصَّلاةِ الَّتي تَلِيها)"
    ),
)

val azkarTopicsList = listOf(
    TopicItem(1, "أذكار الصباح"),
    TopicItem(2, "أذكار المساء"),
    TopicItem(3, "أذكار النوم"),
    TopicItem(4, "أذكار الاستيقاظ"),
    TopicItem(5, "أذكار الخروج من المنزل"),
    TopicItem(6, "أذكار دخول المنزل"),
    TopicItem(7, "أذكار الآذان"),
    TopicItem(8, "أذكار بعد الصلاة"),
    TopicItem(9, "أذكار نزول المطر"),
)

val homeRowItems = listOf(
    HomeRowItems(
        1,
        R.drawable.quran_orangeicon,
        R.string.home_rv_item_text_1,
        color_HomeRvItemColor1
    ),
    HomeRowItems(
        2,
        R.drawable.lantern,
        R.string.home_rv_item_text_2,
        color_HomeRvItemColor2
    ),
    HomeRowItems(
        3,
        R.drawable.duaa,
        R.string.home_rv_item_text_3,
        color_HomeRvItemColor3
    ),
    HomeRowItems(
        4,
        R.drawable.islamic_decoration,
        R.string.home_rv_item_text_4,
        color_HomeRvItemColor4
    ),
    HomeRowItems(
        5,
        R.drawable.question_answer_1,
        R.string.home_rv_item_text_5,
        color_HomeRvItemColor5
    ),
    HomeRowItems(
        6,
        R.drawable.left_arrow,
        R.string.home_rv_item_text_6,
        color_HomeRvItemColor6
    ),
)