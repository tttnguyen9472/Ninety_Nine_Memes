package com.greenfoxacademy.backend.model.meme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemeDTO {


//    Array[ meme object: { memeId, timestamp, metada[funny, cringe, horny stb.] array[comments], img } ]
    private String caption;
    private String url;

}
