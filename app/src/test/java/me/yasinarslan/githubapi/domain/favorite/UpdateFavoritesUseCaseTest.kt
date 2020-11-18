package me.yasinarslan.githubapi.domain.favorite

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UpdateFavoritesUseCaseTest {

	private lateinit var SUT: UpdateFavoritesUseCase

	private val favoriteRepositoryMock = mockk<FavoriteRepository>()

	@BeforeAll
	fun setUp() {
		SUT = UpdateFavoritesUseCase(favoriteRepositoryMock, TestCoroutineDispatcher())
	}

	@Test
	fun `updateFavorites should be called, when the usecase invoked`() = runBlockingTest {
		// given
		coEvery { favoriteRepositoryMock.updateFavorites(-1) } returns mockk()

		// when
		SUT(UpdateFavoritesUseCase.Param(-1))

		// then
		coVerify(exactly = 1) { favoriteRepositoryMock.updateFavorites(-1) }
	}
}