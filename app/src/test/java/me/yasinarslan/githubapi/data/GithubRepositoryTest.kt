package me.yasinarslan.githubapi.data

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import me.yasinarslan.githubapi.common.Error
import me.yasinarslan.githubapi.common.Result
import me.yasinarslan.githubapi.data.github.GithubRepositoryImpl
import me.yasinarslan.githubapi.data.github.GithubService
import me.yasinarslan.githubapi.domain.repository.Repository
import me.yasinarslan.githubapi.network.Network
import me.yasinarslan.githubapi.network.NetworkResponse
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GithubRepositoryTest {

	private val SUT = GithubRepositoryImpl()

	private val githubServiceMock = mockk<GithubService>()

	private val paramUsername = ""

	@BeforeAll
	fun setUp() {
		mockkObject(Network)
		every { Network.buildService(GithubService::class.java) } returns githubServiceMock
	}

	@Test
	fun `when service returns successful response, repository should return success too`() = runBlockingTest {
		// given
		val responseMock = mockk<List<Repository>>()
		val successfulResponse = NetworkResponse.Success(responseMock)
		coEvery { githubServiceMock.listRepositories(paramUsername) } returns successfulResponse

		// when
		val result = SUT.listRepositories(paramUsername)

		// then
		assertTrue(result is Result.Success)
	}

	@Test
	fun `when service returns failure response, repository should return failure too`() = runBlockingTest {
		// given
		val failureResponse: NetworkResponse.Failure<List<Repository>> = NetworkResponse.Failure(Error(""))
		coEvery { githubServiceMock.listRepositories(paramUsername) } returns failureResponse

		// when
		val result = SUT.listRepositories(paramUsername)

		// then
		assertTrue(result is Result.Failure)
	}

	@AfterAll
	fun tearDown() {
		unmockkAll()
	}
}