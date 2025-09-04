package ir.vy.wikipedia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.vy.wikipedia.activities.ExploreActivity
import ir.vy.wikipedia.activities.TrendActivity
import ir.vy.wikipedia.adapter.ExploreAdapter
import ir.vy.wikipedia.adapter.ItemEventsExplore
import ir.vy.wikipedia.adapter.ItemEventsTrend
import ir.vy.wikipedia.data.ItemPost
import ir.vy.wikipedia.databinding.FragmentExploreBinding

const val SEND_DATA_TO_SECOND = "sendData"

class FragmentExplore : Fragment(), ItemEventsExplore, ItemEventsTrend {
    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataExplore = arrayListOf<ItemPost>(

            // Falak-ol-Aflak
            ItemPost(
                "https://en.wikipedia.org/wiki/Falak-ol-Aflak",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Falakolaflak.jpg/500px-Falakolaflak.jpg",
                "Falak-ol-Aflak",
                "Castle in Khorramabad, Lorestan, Iran",
                "Falak ol-Aflak (Persian: فلک الافلاک, 'the sky of the skies') or Shapur Khast Castle (Persian: دژ شاپورخواست) is a castle situated on the top of a large hill with the same name within the city of Khorramabad, the regional capital of Lorestan province, Iran. This gigantic structure was built during the Sasanian era (224–651).\n" +
                        "\n" +
                        "The Khorramabad River runs past the eastern and south-western side of the Falak-ol-Aflak hill providing the fortress some natural protection on those sides. Today, the western and northern sides of the hill are bordered by the residential districts of Khorramabad.\nDuring the Pahlavi era, after being used as a prison until 1968, it was transformed into a museum complex.\n" +
                        "\n" +
                        "The foundations of the actual castle measure approximately 300 by 400 metres (980 ft × 1,310 ft). The height of the entire structure, including the hill, reaches up to 40 meters above the surrounding area.\n" +
                        "\n" +
                        "Archaeological studies have identified the existence of a two layered rampart with twelve towers around the present day construction. From the twelve original towers, only two remain and these are situated northwest and southwest of the existing fortress.\n" + "" +
                        "\n" +
                        "Falak ol-Aflak castle is made with different materials like stone and wood that are vulnerable to humidity. That is why the castle was built on the highest point of the city of Khorramabad, so that the wind could penetrate the building and dry its foundations.",
                //trend
                "https://en.wikipedia.org/wiki/SummerSlam_%282025%29?wprov=sfla1",
                "https://upload.wikimedia.org/wikipedia/en/3/39/SummerSlam_2025_poster.jpg",
                "SummerSlam (2025)",
                "WWE pay-per-view and livestreaming event",
                "The 2025 SummerSlam, also promoted as SummerSlam: New Jersey, was a professional wrestling pay-per-view (PPV) and livestreaming event produced by WWE. It was the 38th annual SummerSlam and took place as a two-night event on Saturday, August 2, and Sunday, August 3, 2025, at MetLife Stadium in East Rutherford, New Jersey, held for wrestlers from the promotion's Raw and SmackDown brands. Rapper Cardi B served as the hostess of the event.\n" +
                        "\n" +
                        "This marked the first SummerSlam to take place across two nights, which was previously only reserved for WrestleMania since 2020. This was the first SummerSlam to broadcast on Netflix in most international markets after the WWE Network merged under the platform in January 2025 in those areas. This was the third WWE event to be held at MetLife Stadium, after WrestleMania 29 and WrestleMania 35 in 2013 and 2019, respectively. This was the fourth SummerSlam to take place in East Rutherford after the 1989, 1997, and 2007 events. The event also featured John Cena's last appearance at a SummerSlam as an in-ring performer due to his retirement from professional wrestling at the end of 2025.\n" +
                        "\n" +
                        "The card comprised a total of 13 matches, with seven on the first night (including an impromptu match) and six on the second. In the main event of Night 1, CM Punk defeated Gunther to win Raw's World Heavyweight Championship, after which, Seth Rollins cashed in his Money in the Bank contract and defeated Punk to win the title. In other prominent matches, Tiffany Stratton defeated Jade Cargill to retain SmackDown's WWE Women's Championship and in the opening bout, Roman Reigns and Jey Uso defeated Bron Breakker and Bronson Reed.\n" +
                        "\n" +
                        "In the main event on Night 2, Cody Rhodes defeated John Cena in a Street Fight to win SmackDown's Undisputed WWE Championship. In other prominent matches, Dominik Mysterio defeated AJ Styles to retain Raw's WWE Intercontinental Championship, The Wyatt Sicks (Dexter Lumis and Joe Gacy) won a Six-Pack Tables, Ladders, and Chairs match to retain SmackDown's WWE Tag Team Championship, and in the opening bout, Naomi defeated Rhea Ripley and Iyo Sky in a triple threat match to retain Raw's Women's World Championship. The second night of the event was notable for the surprise return of Brock Lesnar, who had been on hiatus from WWE since the 2023 edition of SummerSlam.\n" +
                        "\n" +
                        "The event received mostly positive reviews, with the main events of both nights garnering universal acclaim, whilst the opening tag team match, the TLC match, the Women's World Championship match, the Women's Intercontinental Championship match, and Jelly Roll's performance during his bout received widespread praise. Criticism was majorly directed towards the return of Lesnar amidst his involvement in the Vince McMahon sex trafficking scandal.",
                "",
                "524"
            ),
            // battle of warsaw
            ItemPost(
                "https://en.wikipedia.org/wiki/Battle_of_Warsaw_(1705)",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Battle_of_Warsaw_1705.PNG/500px-Battle_of_Warsaw_1705.PNG",
                "Battle of Warsaw (1705)",
                "Swedish victory in the Great Northern War",
                "The Battle of Warsaw (also known as the Battle of Rakowitz or Rakowiec) was fought on 31 July 1705 (Gregorian calendar) near Warsaw in the Polish–Lithuanian Commonwealth, during the Great Northern War and the 1701–1706 Swedish invasion of Poland. The battle was part of a power struggle for the Polish–Lithuanian throne, and was fought between Augustus II the Strong and Stanisław Leszczyński and their allies. Augustus entered the Great Northern War as Elector of Saxony and King of the Polish–Lithuanian Commonwealth, and had formed an alliance with Denmark–Norway and Russia. Stanisław Leszczyński had seized the Polish throne in 1704, with the support of the Swedish army of King Charles XII. The struggle for the throne forced the Polish nobility to pick sides; the Warsaw Confederation supported Leszczyński and Sweden, and the Sandomierz Confederation supported Augustus and his allies. The conflict resulted in the Polish civil war of 1704–1706.",

                //trend
                "https://en.wikipedia.org/wiki/Sydney_Sweeney?wprov=sfla1",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Sydney_Sweeney_at_the_2024_Toronto_International_Film_Festival_%28cropped_2%29.jpg/500px-Sydney_Sweeney_at_the_2024_Toronto_International_Film_Festival_%28cropped_2%29.jpg",
                "Sydney Sweeney",
                "American actress and producer (born 1997)",
                "Sydney Bernice Sweeney was born on September 12, 1997, in Spokane, Washington. Her mother is a former criminal defense lawyer, and her father is a hospitality professional. She has one brother. Sweeney was raised in the Idaho panhandle along the Washington border, at a rural lakeside home that her family has inhabited for five generations.According to Sweeney, she has a \"religious family\".\n" +
                        "\n" +
                        "Sweeney attended school at Saint George's School in Spokane. She was active in numerous sports: \"I was in every single sport possible\", she said. \"I was on the soccer team, the baseball team, the snow slalom ski team, I was wakeboarding.\" Sweeney said she had a wakeboarding accident as a child where the edge of her board propelled backward and sliced the area next to her eye, leaving a permanent scar.\n" +
                        "\n" +
                        "Sweeney became interested in acting after auditioning to be an extra in an independent film that was shooting in the Spokane area. To convince her parents to allow her to pursue acting, she presented them with a five-year business plan. Sweeney began to audition and book commercial acting jobs in Seattle and Portland, Oregon, where the family temporarily resided, until choosing to relocate to Los Angeles when she was 13 years old.\n" +
                        "\n" +
                        "In high school, Sweeney was on the robotics team and participated in the mathematics club. She was valedictorian of her graduating class at Brighton Hall School in Burbank, California. In 2016, Sweeney briefly worked at Universal Studios Hollywood, but left after she had been hired for an acting job. She also briefly attended the University of California, Los Angeles.",
                "",
                "320"
            ),
            // edward drinker
            ItemPost(
                "https://en.wikipedia.org/wiki/Edward_Drinker_Cope",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/Cope_Edward_Drinker_1840-1897.jpg/375px-Cope_Edward_Drinker_1840-1897.jpg",
                "Edward Drinker Cope",
                "American paleontologist and biologist (1840-1897)",
                "Edward Drinker Cope (July 28, 1840 – April 12, 1897) was an American zoologist, paleontologist, comparative anatomist, herpetologist, and ichthyologist. Born to a wealthy Quaker family, he distinguished himself as a child prodigy interested in science, publishing his first scientific paper at the age of 19. Though his father tried to raise Cope as a gentleman farmer, he eventually acquiesced to his son's scientific aspirations.\n" +
                        "\n" +
                        "Cope had little formal scientific training, and he eschewed a teaching position for field work. He made regular trips to the American West, prospecting in the 1870s and 1880s, often as a member of U.S. Geological Survey teams. A personal feud between Cope and paleontologist Othniel Charles Marsh led to a period of intense fossil-finding competition now known as the Bone Wars. Cope's financial fortunes soured after failed mining ventures in the 1880s, forcing him to sell off much of his fossil collection. He experienced a resurgence in his career toward the end of his life before dying on April 12, 1897.\n" +
                        "\n" +
                        "Though Cope's scientific pursuits nearly bankrupted him, his contributions helped to define the field of American paleontology. He was a prodigious writer with 1,400 papers published over his lifetime, although his rivals debated the accuracy of his rapidly published works. He discovered, described, and named more than 1,000 vertebrate species, including hundreds of fishes and dozens of dinosaurs.",

                //trend
                "https://en.wikipedia.org/wiki/The_Fantastic_Four%3A_First_Steps?wprov=sfla1",
                "https://upload.wikimedia.org/wikipedia/en/1/13/The_Fantastic_Four_First_Steps_poster.jpg",
                "The Fantastic Four: First Steps",
                "2025 Marvel Studios film",
                "The Fantastic Four: First Steps is a 2025 American superhero film based on the Marvel Comics superhero team the Fantastic Four. Produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures, it is the 37th film in the Marvel Cinematic Universe (MCU) and the second reboot of the Fantastic Four film series. The film was directed by Matt Shakman from a screenplay by Josh Friedman, Eric Pearson, and the team of Jeff Kaplan and Ian Springer. It features an ensemble cast including Pedro Pascal, Vanessa Kirby, Ebon Moss-Bachrach, and Joseph Quinn as the titular team, alongside Julia Garner, Sarah Niles, Mark Gatiss, Natasha Lyonne, Paul Walter Hauser, and Ralph Ineson. The film is set in the 1960s of a retro-futuristic world which the Fantastic Four must protect from the planet-devouring cosmic being Galactus (Ineson).\n" +
                        "\n" +
                        "20th Century Fox began work on a new Fantastic Four film following the failure of Fantastic Four (2015). After the studio was acquired by Disney in March 2019, control of the franchise was transferred to Marvel Studios, and a new film was announced that July. Jon Watts was set to direct in December 2020, but stepped down in April 2022. Shakman replaced him that September when Kaplan and Springer were working on the script. Casting began by early 2023, and Friedman joined in March to rewrite the script. The film is differentiated from previous Fantastic Four films by avoiding the team's origin story. Pearson joined to polish the script by mid-February 2024, when the main cast and the title The Fantastic Four were announced. The subtitle was added in July, when filming began. It took place until November 2024 at Pinewood Studios in England, and on location in England and Spain.\n" +
                        "\n" +
                        "The Fantastic Four: First Steps premiered at the Dorothy Chandler Pavilion in Los Angeles on July 21, 2025, and was released in the United States on July 25, as the first film in Phase Six of the MCU. It received generally positive reviews from critics and has grossed \$435 million worldwide, making it the tenth-highest-grossing film of 2025 as well the highest-grossing Fantastic Four film. A sequel is in development.",
                "",
                "215"
            ),
            // daily news building
            ItemPost(
                "https://en.wikipedia.org/wiki/Daily_News_Building",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Daily_News_Building-2.jpg/500px-Daily_News_Building-2.jpg",
                "Daily News Building",
                "office Skyscraper in Manhattan, New York",
                "The Daily News Building (also the News Building) is a skyscraper at 220 East 42nd Street in the East Midtown neighborhood of Manhattan, New York City, United States. The original tower, designed by Raymond Hood and John Mead Howells in the Art Deco style and completed in 1930, was one of several major developments constructed on 42nd Street around that time. A similarly-styled expansion, designed by Harrison & Abramovitz, was completed in 1960. When it originally opened, the building received mixed reviews and was described as having a utilitarian design. The Daily News Building is a National Historic Landmark, and its exterior and lobby are New York City designated landmarks.\n" +
                        "\n" +
                        "The edifice occupies a rectangular site adjoined by 41st Street to the south, Second Avenue to the east, and 42nd Street to the north. It consists of a 36-story tower rising 476 feet (145 m), along with a 14-story printing plant on 41st Street and an 18-story annex on 42nd Street. There is a large carved-granite entrance at 42nd Street, leading to a rotunda lobby with a rotating painted globe. The facade is divided vertically into bays of windows separated by white-brick sections of wall, with brick spandrel panels between windows on different stories. The massing, or general shape, includes several setbacks on higher floors.\n" +
                        "\n" +
                        "After the New York Daily News acquired land on 42nd Street in February 1928, the paper's founder Joseph Medill Patterson commissioned Hood and Howells to design a building there. The architects filed blueprints with the Manhattan Bureau of Buildings in June 1928, and the Daily News started moving into the building in February 1930, with the lobby opening that July. The newspaper filed plans in 1944 for the annex, work on which began in 1957 after additional land was acquired. The Daily News' parent, Tribune Media, sold the building in 1982 to a limited partnership led by the La Salle Street Fund. The newspaper downsized its offices there over the next decade before moving out entirely in 1995, and its space was rented out to other tenants. SL Green Realty bought the building in 2003 and sold a partial ownership stake to Meritz Alternative Investment Management in 2021.",

                //trend
                "https://en.wikipedia.org/wiki/Superman_%282025_film%29?wprov=sfla1",
                "https://upload.wikimedia.org/wikipedia/en/3/32/Superman_%282025_film%29_poster.jpg",
                "Superman (2025 film)",
                "2025 DC Studios film",
                "Superman is a 2025 American superhero film based on the eponymous character from DC Comics. Written and directed by James Gunn, it is the first film in the DC Universe (DCU) and a reboot of the Superman film series. David Corenswet stars as Clark Kent / Superman, alongside Rachel Brosnahan, Nicholas Hoult, Edi Gathegi, Anthony Carrigan, Nathan Fillion, and Isabela Merced. In the film, Superman faces unintended consequences after he intervenes in an international conflict orchestrated by billionaire Lex Luthor (Hoult). Superman must win back public support with the help of his reporter and superhero colleagues. The film was produced by Gunn and Peter Safran of DC Studios.\n" +
                        "\n" +
                        "Development on a sequel to the DC Extended Universe (DCEU) film Man of Steel (2013) began by October 2014, with Henry Cavill set to reprise his role as Superman. Plans changed after the troubled production of Justice League (2017) and the Man of Steel sequel was no longer moving forward by May 2020. Gunn began work on a new Superman film around August 2022. In October, he became co-CEO of DC Studios with Safran and they began work on a new DC Universe. Gunn was publicly revealed to be writing the film in December. The title Superman: Legacy was announced the next month, Gunn was confirmed to be directing in March 2023, and Corenswet and Brosnahan (Lois Lane) were cast that June. The subtitle was dropped by the end of February 2024, when filming began in Svalbard, Norway. Production primarily took place at Trilith Studios in Atlanta, Georgia, with location filming around Georgia and Ohio. Filming wrapped in July. The film's influences include the comic book All-Star Superman (2005–2008) by Grant Morrison and Frank Quitely.\n" +
                        "\n" +
                        "Superman premiered at the TCL Chinese Theater on July 7, 2025, and was released by Warner Bros. Pictures in the United States on July 11. It is the first film in the DCU's Chapter One: Gods and Monsters. The film has grossed \$579 million worldwide, making it the seventh-highest-grossing film of 2025, and received mostly positive reviews. Critics found it to be fun, colorful, and earnest, although some felt it was overstuffed, while the performances of Corenswet, Brosnahan, and Hoult were praised.",
                "",
                "164"
            )
        )
        val exploreAdapter = ExploreAdapter(dataExplore.clone() as ArrayList<ItemPost>, this, this)

        binding.recyclerExplore.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerExplore.adapter = exploreAdapter

        // Search in items
        binding.editSearch.addTextChangedListener { searchInput ->
            // بررسی امن ورودی جستجو
            if (searchInput?.isNotEmpty() == true) {
                // ایجاد کپی از لیست اصلی
                val filteredList = dataExplore.filter { itemPost ->
                    // جستجوی غیرحساس به حروف کوچک و بزرگ
                    itemPost.txtTitle.contains(searchInput, ignoreCase = true)
                }
                // تنظیم داده‌های فیلتر شده
                exploreAdapter.setData(ArrayList(filteredList))
            } else {
                // نمایش تمام داده‌ها با کپی امن
                exploreAdapter.setData(ArrayList(dataExplore))
            }
        }
    }

    override fun onItemClicked(itemPost: ItemPost) {

        val intent = Intent(activity, ExploreActivity::class.java)
        intent.putExtra(SEND_DATA_TO_SECOND, itemPost)
        startActivity(intent)

//        val bundle = Bundle()
//        val fragment = childFragmentManager.beginTransaction()
//        fragment.add(R.id.frame_main, FragmentVideomaker())
//        fragment.addToBackStack(null)
//        fragment.commit()
    }

    override fun onItemClickedTrend(itemPost: ItemPost) {

        val intent = Intent(activity, TrendActivity::class.java)
        intent.putExtra(SEND_DATA_TO_SECOND, itemPost)
        startActivity(intent)
    }
}