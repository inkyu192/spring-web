package spring.web.kotlin.config.security

import io.jsonwebtoken.Claims
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import spring.web.kotlin.constant.Role
import spring.web.kotlin.domain.Member

class UserDetailsImpl private constructor(
    private val memberId: Long,
    private val account: String,
    private val password: String?,
    private val role: Role
) : UserDetails {
    constructor(member: Member) : this(
        memberId = member.id!!,
        account = member.account,
        password = member.password,
        role = member.role
    )

    constructor(claims: Claims) : this(
        memberId = claims["memberId"] as Long,
        account = claims["account"].toString(),
        password = null,
        role = Role.of(claims["role"])
    )

    override fun getAuthorities() = mutableListOf(SimpleGrantedAuthority(role.name))

    override fun getPassword() = password

    override fun getUsername() = account

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}