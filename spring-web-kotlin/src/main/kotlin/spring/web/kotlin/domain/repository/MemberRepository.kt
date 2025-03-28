package spring.web.kotlin.domain.repository

import spring.web.kotlin.domain.model.entity.Member

interface MemberRepository {
    fun findByIdOrNull(id: Long): Member?
    fun findByAccount(account: String): Member?
    fun existsByAccount(account: String): Boolean
    fun save(member: Member): Member
    fun delete(member: Member)
}