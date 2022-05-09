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
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
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
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType(MediaType.APPLICATION_JSON)) }
                    jsonPath("$.accountNumber") { value(bank.accountNumber) }

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


}