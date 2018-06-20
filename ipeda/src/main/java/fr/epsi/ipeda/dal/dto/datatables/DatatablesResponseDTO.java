package fr.epsi.ipeda.dal.dto.datatables;

public class DatatablesResponseDTO {

	private int draw;
	private Object data;
	private int recordsTotal;
	private int recordsFiltered;

	public DatatablesResponseDTO(DatatablesRequestDTO datatablesRequestDTO) {
		prepareDataTableResponse(datatablesRequestDTO);
	}

	private void prepareDataTableResponse(DatatablesRequestDTO datatablesRequestDTO) {
		this.setDraw(Integer.parseInt(datatablesRequestDTO.getDraw()));
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

}
