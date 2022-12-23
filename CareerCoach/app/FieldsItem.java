public class FieldsItem{

	@SerializedName("id")
	private String id;

	@SerializedName("label")
	private String label;

	@SerializedName("type")
	private String type;

	public String getId(){
		return id;
	}

	public String getLabel(){
		return label;
	}

	public String getType(){
		return type;
	}
}
