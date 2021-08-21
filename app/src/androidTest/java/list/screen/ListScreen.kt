package list.screen

import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

import me.yasinarslan.githubapi.R


class ListScreen(override val viewClass: Class<*>? = null):
	KScreen<ListScreen>()  {

	override val layoutId: Int = R.layout.fragment_list

	val searchBar = KEditText{
		withId(R.id.searchBar)
	}

	val searchButton = KButton{
		withId(R.id.searchButton)
	}

	fun checkSearchBarText(){
		searchButton.hasHint("Username")
	}

	fun checkSearchButtonText(){
		searchButton.hasText("SEARCH")
	}

	fun fillSearchBar(username :String){
		searchBar{
			click()
			replaceText("saygindikbayir")
		}
	}

	fun clickSearchButton(){
		searchButton.click()
	}

}