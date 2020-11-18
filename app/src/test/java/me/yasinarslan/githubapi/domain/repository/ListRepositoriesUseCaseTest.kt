package me.yasinarslan.githubapi.domain.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import me.yasinarslan.githubapi.common.Error
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.domain.favorite.ListFavoritesUseCase
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ListRepositoriesUseCaseTest {
	private lateinit var SUT: ListRepositoriesUseCase

	private val githubRepository = mockk<GithubRepository>()
	private val listFavoritesUseCase = mockk<ListFavoritesUseCase>()

	private val paramUsername = ListRepositoriesUseCase.Param("")

	@BeforeAll
	fun setUp() {
		SUT = ListRepositoriesUseCase(githubRepository, listFavoritesUseCase, TestCoroutineDispatcher())
	}

	@Test
	fun `listRepositories should be called, when the usecase invoked`() = runBlockingTest {
		// given

		// when
		SUT(paramUsername)

		// then
		coVerify { githubRepository.listRepositories("") }
	}

	@Test
	fun `when listRepositories returns Success, listFavoritesUseCase should be called`() = runBlockingTest {
		// given
		val mockResponse = mockk<List<Repository>>()
		coEvery { githubRepository.listRepositories("") } returns Result.Success(mockResponse)

		// when
		SUT(paramUsername)

		// then
		coVerify { listFavoritesUseCase.invoke(Unit) }
	}

	@Test
	fun `when listRepositories returns Failure, usecase should return Failure too`() = runBlockingTest {
		// given
		coEvery { githubRepository.listRepositories("") } returns Result.Failure(Error(""))

		// when
		val result = SUT(paramUsername)

		// then
		assertTrue(result is Result.Failure)
	}
}
