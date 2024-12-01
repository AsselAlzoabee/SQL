package csvImport;

public class ConcreteReaderCreator extends ReaderCreator {

	@Override
	public ReaderProduct factoryMethod(String typ) {

		// check bei einer csv übergabe ob es exisitiert oder nicht
		if ("csv".equals(typ)) {
			return new ConcreteCsvReaderProduct();
		} else {
			return new ConcreteTxtReaderProduct();
		}

	}
}
