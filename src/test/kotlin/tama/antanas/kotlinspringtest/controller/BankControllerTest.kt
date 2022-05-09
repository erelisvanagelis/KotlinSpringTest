package tama.antanas.kotlinspringtest.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*
import org.springframework.web.servlet.function.RequestPredicates
import tama.antanas.kotlinspringtest.model.Bank

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {
    val baseUrl = "/api/banks/"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should GET all banks`() {
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType(MediaType.APPLICATION_JSON)) }
                    jsonPath("$[0].accountNumber") { value("000") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should GET specified bank`() {
            //given
            val accountNumber = "000"

            //when/then
            mockMvc.get(baseUrl + accountNumber)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType(MediaType.APPLICATION_JSON)) }
                    jsonPath("$.accountNumber") { value("000") }
                }
        }

        @Test
        fun `should return not found exception`() {
            //given
            val accountNumber = "doesNotExists"

            //when/then
            mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostBank {
        @Test
        fun `should POST bank`() {
            //given
            val bank = Bank("naujas", 12.3, 4)

            //when
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }

            //then
            mockMvc.get(baseUrl + bank.accountNumber)
                .andExpect {
                    content {
                        RequestPredicates.contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(bank))
                    }
                }
        }

        @Test
        fun `should throw illegal argument exception`() {
            //given
            val bank = Bank("000", 12.3, 4)

            //when
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchBank {
        @Test
        fun `should patch bank`() {
            //given
            val bank = Bank("000", 3.9, 20)

            //when
            mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }

            //then
            mockMvc.get(baseUrl + bank.accountNumber)
                .andExpect {
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(bank))
                    }
                }
        }

        @Test
        fun `should throw not found exception`() {
            //given
            val bank = Bank("tokio nera", 3.9, 20)

            //when
            mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteBank {
        @Test
        @DirtiesContext
        fun `should delete a bank`() {
            //given
            val accountNumber = "000"
            //when
            mockMvc.delete("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }
            //then
            mockMvc.get("/api/banks/$accountNumber")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return not found exception`() {
            //given
            val accountNumber = "doesn't exist"
            //when
            mockMvc.delete("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}