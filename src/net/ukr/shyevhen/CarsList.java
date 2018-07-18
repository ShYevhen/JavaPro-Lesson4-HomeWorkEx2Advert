package net.ukr.shyevhen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarsList {

	private List<Car> carList = Collections.synchronizedList(new ArrayList<>());

	public CarsList(List<Car> carList) {
		super();
		this.carList = carList;
	}

	public CarsList() {
		super();
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public List<Car> get(String param, String value) {
		List<Car> resp = new ArrayList<>();
		for (Car car : carList) {
			if (param.equals("brand") && car.getBrand().equalsIgnoreCase(value)
					|| param.equals("model") && car.getModel().equalsIgnoreCase(value)) {
				resp.add(car);
			}
		}
		return resp;
	}

	public List<Car> get(String param, int value) {
		List<Car> resp = new ArrayList<>();
		for (Car car : carList) {
			if (param.equals("year") && car.getYear() == value) {
				resp.add(car);
			}
		}
		return resp;
	}

	public List<Car> get(int pMin, int pMax) {
		List<Car> resp = new ArrayList<>();
		for (Car car : carList) {
			if (pMin <= car.getPrice() && pMax >= car.getPrice()) {
				resp.add(car);
			}
		}
		return resp;
	}

	public Car get(int hash) {
		for (Car car : carList) {
			if (car.hashCode() == hash) {
				return car;
			}
		}
		return null;
	}

	public void addAdvert(Car car) {
		carList.add(car);
	}

	public void delAdvert(String user, int hash) {
		Car car = get(hash);
		if (car != null && car.getUser().equals(user)) {
			carList.remove(car);
		}
	}
}
