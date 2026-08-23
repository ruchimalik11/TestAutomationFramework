package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JSONUtility {

	public static Environment readJSON(Env env) {

	    Gson gson = new Gson();

	    InputStream is = JSONUtility.class.getClassLoader()
	            .getResourceAsStream("config/config.json");

	    if (is == null) {
	        throw new RuntimeException("config.json not found in classpath");
	    }

	    Config config = gson.fromJson(new InputStreamReader(is), Config.class);

	    return config.getEnvironments().get(env.name());
	}
}
