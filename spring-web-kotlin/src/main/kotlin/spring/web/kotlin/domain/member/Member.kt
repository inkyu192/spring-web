package spring.web.kotlin.domain.member

import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.persistence.*
import spring.web.kotlin.domain.Address
import spring.web.kotlin.domain.Base
import spring.web.kotlin.global.converter.persistence.CryptoAttributeConverter

@Entity
class Member protected constructor(
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null,
    var account: String,
    var password: String,

    @Convert(converter = CryptoAttributeConverter::class)
    var name: String,

    @Enumerated(EnumType.STRING)
    var role: Role,

    @Embedded
    var address: Address
) : Base() {
    companion object {
        fun create(account: String, password: String, name: String, role: Role, address: Address) =
            Member(
                account = account,
                password = password,
                name = name,
                role = role,
                address = address
            )
    }

    fun update(name: String, role: Role, address: Address) {
        this.name = name
        this.role = role
        this.address = address
    }

    enum class Role(
        val description: String
    ) {
        ROLE_ADMIN("어드민"),
        ROLE_BUYER("소비자"),
        ROLE_SELLER("판매자");

        companion object {
            @JvmStatic
            @JsonCreator
            fun of(name: Any?) = entries.find { it.name == name }
        }
    }
}
