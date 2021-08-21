package list.test

import ActivityBaseTest
import extensions.getLastFragment
import list.screen.ListScreen
import me.yasinarslan.githubapi.presentation.MainActivity
import me.yasinarslan.githubapi.presentation.list.ListFragment
import org.junit.Test

class ListScreenTest: ActivityBaseTest<MainActivity>(MainActivity::class.java)
{
	private val listScreen = ListScreen()

	@Test
	fun DirectlyOpeningListFragment(){
		listScreen {
			//step("Checks the screen opened without pre-screen") {
				activityScenario?.onActivity {
					val nextFragment = it.getLastFragment()
					assertTrue(nextFragment::class.java == ListFragment::class.java)
				}
			//}
		}
	}
}