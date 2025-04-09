package com.yedam.control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;
import com.yedam.vo.EventVO;

public class RemoveEventControl implements Control {

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 한글 인코딩 처리
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json;charset=UTF-8");

            // JSON 데이터 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            // JSON 파싱
            String title = URLDecoder.decode(json.get("title").getAsString(), "UTF-8");
            String start = json.get("startDate").getAsString();
            String end = json.get("endDate").getAsString();

            // VO 세팅
            EventVO vo = new EventVO();
            vo.setTitle(title);
            vo.setStartDate(start);
            vo.setEndDate(end);

            // 서비스 처리
            EventService svc = new EventServiceImpl();
            boolean result = svc.removeEvent(vo);

            // 결과 반환
            PrintWriter out = resp.getWriter();
            JsonObject ret = new JsonObject();
            if (result) {
                ret.addProperty("retCode", "OK");
            } else {
                ret.addProperty("retCode", "Fail");
            }
            out.print(ret.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
