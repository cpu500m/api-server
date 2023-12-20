package server.handler;

import server.dto.Start;

public class StartHandler extends JsonHttpHandler{
    @Override
    public Start handle() {
        return new Start("OK");
    }
}
