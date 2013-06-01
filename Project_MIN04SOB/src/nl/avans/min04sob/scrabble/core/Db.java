package nl.avans.min04sob.scrabble.core;

import java.sql.ResultSet;
import java.util.concurrent.Future;

import testmains.TestMain;

public class Db {

	public static Future<ResultSet> run(Query q){
		return TestMain.executor.submit(q);
	}
}
