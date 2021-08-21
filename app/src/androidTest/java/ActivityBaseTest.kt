import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class ActivityBaseTest<T: Activity?>(
	private val kClass: Class<T>,
	private val intent: Intent? = null
): TestCase(){

var activityScenario: ActivityScenario<T>? = null

@get:Rule
val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
	Manifest.permission.WRITE_EXTERNAL_STORAGE,
	Manifest.permission.READ_EXTERNAL_STORAGE
)


	@Before
	override fun setUp() {
		activityScenario = ActivityScenario.launch(
			intent ?: Intent(ApplicationProvider.getApplicationContext(), kClass)
		)
	}

	@After
	override fun tearDown() {
		activityScenario?.close()
	}

}