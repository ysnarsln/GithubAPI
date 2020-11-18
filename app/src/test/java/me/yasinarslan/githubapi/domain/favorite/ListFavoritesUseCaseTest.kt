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
internal class ListFavoritesUseCaseTest {

	private lateinit var SUT: ListFavoritesUseCase

	private val favoriteRepositoryMock = mockk<FavoriteRepository>()

	@BeforeAll
	fun setUp() {
		SUT = ListFavoritesUseCase(favoriteRepositoryMock, TestCoroutineDispatcher())
	}

	@Test
	fun `listFavorites should be called, when the usecase invoked`() = runBlockingTest {
		// given
		coEvery { favoriteRepositoryMock.listFavorites() } returns mockk()

		// when
		SUT(Unit)

		// then
		coVerify(exactly = 1) { favoriteRepositoryMock.listFavorites() }
	}

}