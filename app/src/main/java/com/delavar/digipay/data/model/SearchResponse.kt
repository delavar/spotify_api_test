import com.google.gson.annotations.SerializedName

data class SearchResponse (

	@SerializedName("artists") val artists : PaginateResponse<Artist>
)