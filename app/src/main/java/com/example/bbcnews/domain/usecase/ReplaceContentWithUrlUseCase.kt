package usecase

class ReplaceContentWithUrlUseCase {
    operator fun invoke(content: String): String {
        val idx = content.indexOf(" [+")
        return if (idx != -1) {
            val replacement = " For the complete article, please visit: "
            val updatedContent = StringBuilder(content)
            updatedContent.replace(idx, idx + replacement.length, replacement)
            updatedContent.toString()
        } else {
            content
        }
    }
}
