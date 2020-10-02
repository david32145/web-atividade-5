package webservlet.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import webservlet.adapters.GsonAdapter;
import webservlet.dao.CarDao;
import webservlet.models.Car;

@WebServlet("/api/cars/*")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gsonParse = GsonAdapter.getGson();
		
		BufferedReader bufferedReader = request.getReader();
		Car car = gsonParse.fromJson(bufferedReader, Car.class);
		
		car = CarDao.getInstance().addCar(car);
		response.getWriter().print(gsonParse.toJson(car));
		response.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path =  request.getRequestURI();
		if(path.matches("/webservlet/api/cars(/)?")) {
			getAll(request, response);
			return;
		}
		if(path.matches("/webservlet/api/cars/(.)*(/)?")) {
			try {
				String idAsString = path.split("/")[4];
				int id = Integer.parseInt(idAsString);
				getAllById(response, id);
				return;
			} catch (NumberFormatException e) {
				response.getWriter().print("{ \"error\": \"id must be an integer\"  }");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return;
	}
	
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gsonParse = GsonAdapter.getGson();
		Integer limit = null;
		String limitAsString = request.getParameter("limit");
		if(limitAsString != null && !limitAsString.isBlank()) {
			try {
				limit = Integer.parseInt(limitAsString);
			}catch (NumberFormatException e) {
				response.getWriter().print("{ \"error\": \"query param limit must be an integer\"  }");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
		String brand = request.getParameter("brand");
		Collection<Car> cars = CarDao.getInstance().getCars(limit, brand);
		response.getWriter().print(gsonParse.toJson(cars));
		response.setContentType("application/json");
		return;
	}
	
	private void getAllById(HttpServletResponse response, int id) throws IOException {
		Gson gsonParse = GsonAdapter.getGson();
		Car car = CarDao.getInstance().getCarById(id);
		if(car == null) {
			response.getWriter().print("{ \"error\": \"car not found\"  }");
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		response.getWriter().print(gsonParse.toJson(car));
		response.setContentType("application/json");
		return;
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path =  request.getRequestURI();
		if(path.matches("/webservlet/api/cars/(.)*(/)?")) {
			try {
				String idAsString = path.split("/")[4];
				int id = Integer.parseInt(idAsString);
				updateById(response, request, id);
				return;
			} catch (NumberFormatException e) {
				response.getWriter().print("{ \"error\": \"id must be an integer\"  }");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path =  request.getRequestURI();
		if(path.matches("/webservlet/api/cars/(.)*(/)?")) {
			try {
				String idAsString = path.split("/")[4];
				int id = Integer.parseInt(idAsString);
				deleteById(response, id);
				return;
			} catch (NumberFormatException e) {
				response.getWriter().print("{ \"error\": \"id must be an integer\"  }");
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
		}
	}
	
	private void deleteById(HttpServletResponse response, int id) throws IOException {
		CarDao carDao = CarDao.getInstance();
		if(!carDao.carExistsById(id)) {
			response.getWriter().print("{ \"error\": \"car not found\"  }");
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		carDao.deleteById(id);
		return;
	}
	
	private void updateById(HttpServletResponse response, HttpServletRequest request,int id) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gsonParse = GsonAdapter.getGson();
		Car car = gsonParse.fromJson(request.getReader(), Car.class);
		CarDao carDao = CarDao.getInstance();
		if(!carDao.carExistsById(id)) {
			response.getWriter().print("{ \"error\": \"car not found\"  }");
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		car = carDao.updateById(car, id);
		response.getWriter().print(gsonParse.toJson(car));
		response.setContentType("application/json");
	}
}
