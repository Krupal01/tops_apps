public class Response{

	@SerializedName("data")
	private List<String> data;

	@SerializedName("fields")
	private List<FieldsItem> fields;

	public List<String> getData(){
		return data;
	}

	public List<FieldsItem> getFields(){
		return fields;
	}
}
