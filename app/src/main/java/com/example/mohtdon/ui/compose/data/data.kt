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

data class AzkarItem(
    val id: Int,
    val text: String,
    val count: Int
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

val azkarList = listOf(
    AzkarItem(
        1,
        "اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.",
        3
    ),
    AzkarItem(
        2,
        "قُلْ هُوَ ٱللَّهُ أَحَدٌ، ٱللَّهُ ٱلصَّمَدُ، لَمْ يَلِدْ وَلَمْ يُولَدْ، وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌۢ.",
        3
    ),
    AzkarItem(
        3,
        "قُلْ أَعُوذُ بِرَبِّ ٱلْفَلَقِ، مِن شَرِّ مَا خَلَقَ، وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ، وَمِن شَرِّ ٱلنَّفَّٰثَٰتِ فِى ٱلْعُقَدِ، وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ.",
        3
    ),
    AzkarItem(
        4,
        "قُلْ أَعُوذُ بِرَبِّ ٱلنَّاسِ، مَلِكِ ٱلنَّاسِ، إِلَٰهِ ٱلنَّاسِ، مِن شَرِّ ٱلْوَسْوَاسِ ٱلْخَنَّاسِ، ٱلَّذِى يُوَسْوِسُ فِى صُدُورِ ٱلنَّاسِ، مِنَ ٱلْجِنَّةِ وَٱلنَّاسِ.",
        3
    ),
    AzkarItem(
        5,
        "أصبحنا وأصبح الملك لله، والحمد لله، لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير، ربّ أسألك خير ما في هذا اليوم وخير ما بعده وأعوذ بك من شر ما في هذا اليوم وشر ما بعده ربّ أعوذ بك من الكسل وسوء الكبر، ربّ أعوذ بك من عذاب في النار وعذاب في القبر.",
        1
    ),
    AzkarItem(
        6,
        "اللهـم أنت ربـي لا إله إلا أنت ، خلقتنـي وأنا عبـدك ، وأنا علـى عهـدك ووعـدك ما استـطعـت ، أعـوذ بك من شـر ما صنـعت ، أبـوء لـك بنعـمتـك علـي وأبـوء بذنـبي فاغفـر لي فإنـه لا يغـفر الذنـوب إلا أنت.",
        1
    ),
    AzkarItem(7, "رضيـت بالله ربـا وبالإسلام ديـنا وبمحـمد صلى الله عليه وسلم نبيـا.", 3),
    AzkarItem(
        8,
        "اللهـم إنـي أصبـحت أشـهدك ، وأشـهد حملـة عـرشـك ، وملائكتك ، وجمـيع خلـقك ، أنـك أنـت الله لا إله إلا أنـت وحـدك لا شريك لـك ، وأن محمـدا عبـدك ورسـولـك.",
        4
    ),
    AzkarItem(
        9,
        "اللهـم ما أصبـح بي مـن نعـمة أو بأحـد مـن خلـقك ، فمـنك وحـدك لا شريك لـك ، فلـك الحمـد ولـك الشكـر.",
        1
    ),
)