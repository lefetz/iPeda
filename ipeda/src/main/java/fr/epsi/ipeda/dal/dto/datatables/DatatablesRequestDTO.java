package fr.epsi.ipeda.dal.dto.datatables;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import fr.epsi.ipeda.helpers.EmptyUtils;

public class DatatablesRequestDTO {

	/** The unique id. */
	private String uniqueId;

	/** The draw. */
	private String draw;

	/** The start. */
	private Integer start;

	/** The length. */
	private Integer length;

	/** The search. */
	private String search;

	/** The regex. */
	private boolean regex;

	/** The columns. */
	private List<DatatablesColumnDTO> columns;

	/** The order. */
	private DatatablesColumnDTO order;

	/** The is global search. */
	private boolean isGlobalSearch;

	/**
	 * Instantiates a new data table request.
	 *
	 * @param request
	 *            the request
	 */
	public DatatablesRequestDTO(HttpServletRequest request) {
		prepareDataTableRequest(request);
	}

	/**
	 * Prepare data table request.
	 *
	 * @param request
	 *            the request
	 */
	private void prepareDataTableRequest(HttpServletRequest request) {

		Enumeration<String> parameterNames = request.getParameterNames();

		if (parameterNames.hasMoreElements()) {

			this.setStart(Integer.parseInt(request.getParameter("start")));
			this.setLength(Integer.parseInt(request.getParameter("length")));
			this.setUniqueId(request.getParameter("_"));
			this.setDraw(request.getParameter("draw"));
			this.setSearch(request.getParameter("search[value]"));
			this.setRegex(Boolean.valueOf(request.getParameter("search[regex]")));

			int sortableCol = Integer.parseInt(request.getParameter("order[0][column]"));

			List<DatatablesColumnDTO> columns = new ArrayList<DatatablesColumnDTO>();

			if (!EmptyUtils.isObjectEmpty(this.getSearch())) {
				this.setGlobalSearch(true);
			}

			maxParamsToCheck = getNumberOfColumns(request);

			for (int i = 0; i < maxParamsToCheck; i++) {
				if (null != request.getParameter("columns[" + i + "][data]") && !"null".equalsIgnoreCase(request.getParameter("columns[" + i + "][data]"))
						&& !EmptyUtils.isObjectEmpty(request.getParameter("columns[" + i + "][data]"))) {
					DatatablesColumnDTO colSpec = new DatatablesColumnDTO(request, i);
					if (i == sortableCol) {
						this.setOrder(colSpec);
					}
					columns.add(colSpec);

					if (!EmptyUtils.isObjectEmpty(colSpec.getSearch())) {
						this.setGlobalSearch(false);
					}
				}
			}

			if (!EmptyUtils.isObjectEmpty(columns)) {
				this.setColumns(columns);
			}
		}
	}

	private int getNumberOfColumns(HttpServletRequest request) {
		Pattern p = Pattern.compile("columns\\[[0-9]+\\]\\[data\\]");
		@SuppressWarnings("rawtypes")
		Enumeration params = request.getParameterNames();
		List<String> lstOfParams = new ArrayList<String>();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			Matcher m = p.matcher(paramName);
			if (m.matches()) {
				lstOfParams.add(paramName);
			}
		}
		return lstOfParams.size();
	}

	/**
	 * Gets the pagination request.
	 *
	 * @return the pagination request
	 */
//	public PaginationCriteria getPaginationRequest() {
//
//		PaginationCriteria pagination = new PaginationCriteria();
//		pagination.setPageNumber(this.getStart());
//		pagination.setPageSize(this.getLength());
//
//		SortBy sortBy = null;
//		if (!AppUtil.isObjectEmpty(this.getOrder())) {
//			sortBy = new SortBy();
//			sortBy.addSort(this.getOrder().getData(), SortOrder.fromValue(this.getOrder().getSortDir()));
//		}
//
//		FilterBy filterBy = new FilterBy();
//		filterBy.setGlobalSearch(this.isGlobalSearch());
//		for (DatatablesColumnDTO colSpec : this.getColumns()) {
//			if (colSpec.isSearchable()) {
//				if (!AppUtil.isObjectEmpty(this.getSearch()) || !AppUtil.isObjectEmpty(colSpec.getSearch())) {
//					filterBy.addFilter(colSpec.getData(), (this.isGlobalSearch()) ? this.getSearch() : colSpec.getSearch());
//				}
//			}
//		}
//
//		pagination.setSortBy(sortBy);
//		pagination.setFilterBy(filterBy);
//
//		return pagination;
//	}

	/** The max params to check. */
	private int maxParamsToCheck = 0;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isRegex() {
		return regex;
	}

	public void setRegex(boolean regex) {
		this.regex = regex;
	}

	public List<DatatablesColumnDTO> getColumns() {
		return columns;
	}

	public void setColumns(List<DatatablesColumnDTO> columns) {
		this.columns = columns;
	}

	public DatatablesColumnDTO getOrder() {
		return order;
	}

	public void setOrder(DatatablesColumnDTO order) {
		this.order = order;
	}

	public boolean isGlobalSearch() {
		return isGlobalSearch;
	}

	public void setGlobalSearch(boolean isGlobalSearch) {
		this.isGlobalSearch = isGlobalSearch;
	}

}
