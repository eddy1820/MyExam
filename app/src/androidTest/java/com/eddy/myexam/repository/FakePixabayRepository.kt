package com.eddy.myexam.repository

import com.eddy.myexam.model.ImageResponse
import com.eddy.myexam.model.Resource
import com.google.gson.Gson

class FakePixabayRepository : IPixabayRepository {
    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        val data = Gson().fromJson(json, ImageResponse::class.java)
        return Resource.success(data)
    }

    val json = "{\n" +
            "  \"total\": 5,\n" +
            "  \"totalHits\": 5,\n" +
            "  \"hits\": [\n" +
            "    {\n" +
            "      \"id\": 2453376,\n" +
            "      \"pageURL\": \"https://pixabay.com/illustrations/q-and-a-question-answer-q-sign-2453376/\",\n" +
            "      \"type\": \"illustration\",\n" +
            "      \"tags\": \"q and a, question, answer\",\n" +
            "      \"previewURL\": \"https://cdn.pixabay.com/photo/2017/06/29/07/50/q-and-a-2453376_150.jpg\",\n" +
            "      \"previewWidth\": 150,\n" +
            "      \"previewHeight\": 60,\n" +
            "      \"webformatURL\": \"https://pixabay.com/get/g838c256e87be0dcc7fda0e2b6560ff135a946790e4717fad0ce10ff65c6ba8f1704cb2ccaa7a87c66648cdc263115efb704e92cdcb9c4928b5a9b4def1948388_640.jpg\",\n" +
            "      \"webformatWidth\": 640,\n" +
            "      \"webformatHeight\": 256,\n" +
            "      \"largeImageURL\": \"https://pixabay.com/get/g3ec70fb54267aab50561aaeaa8163cc6948c69f17ba14b107455c28b9c87516a83f676ece2a76ba4b95dbb28a0edcd5fbf4d14d2d84df50e51f148db689a05bd_1280.jpg\",\n" +
            "      \"imageWidth\": 6000,\n" +
            "      \"imageHeight\": 2409,\n" +
            "      \"imageSize\": 3097731,\n" +
            "      \"views\": 27239,\n" +
            "      \"downloads\": 12232,\n" +
            "      \"collections\": 369,\n" +
            "      \"likes\": 84,\n" +
            "      \"comments\": 21,\n" +
            "      \"user_id\": 5100055,\n" +
            "      \"user\": \"Josethestoryteller\",\n" +
            "      \"userImageURL\": \"https://cdn.pixabay.com/user/2017/06/15/08-29-16-278_250x250.jpg\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1590024,\n" +
            "      \"pageURL\": \"https://pixabay.com/illustrations/door-open-doorway-entrance-1590024/\",\n" +
            "      \"type\": \"illustration\",\n" +
            "      \"tags\": \"door, open, doorway\",\n" +
            "      \"previewURL\": \"https://cdn.pixabay.com/photo/2016/08/13/00/11/door-1590024_150.jpg\",\n" +
            "      \"previewWidth\": 150,\n" +
            "      \"previewHeight\": 79,\n" +
            "      \"webformatURL\": \"https://pixabay.com/get/g98363894d1f71f2c2b8ee2f625bb93c4659e3ed42c8ea2450f27f70af52080765a0266f5e1b2fc4c45d925e4f00235109416db0089a260abebeb9dd3d7d08c03_640.jpg\",\n" +
            "      \"webformatWidth\": 640,\n" +
            "      \"webformatHeight\": 341,\n" +
            "      \"largeImageURL\": \"https://pixabay.com/get/g705858f5163b16bd4fc8ae79c8ebc2e574b6e22b1e4f132c8a6e1664012bedf01354125dd8838c97f576257f75d2f3ec92935647df9ced3f207f50c553d359ab_1280.jpg\",\n" +
            "      \"imageWidth\": 3000,\n" +
            "      \"imageHeight\": 1600,\n" +
            "      \"imageSize\": 1011940,\n" +
            "      \"views\": 86603,\n" +
            "      \"downloads\": 43995,\n" +
            "      \"collections\": 341,\n" +
            "      \"likes\": 354,\n" +
            "      \"comments\": 53,\n" +
            "      \"user_id\": 1962238,\n" +
            "      \"user\": \"qimono\",\n" +
            "      \"userImageURL\": \"https://cdn.pixabay.com/user/2016/03/14/20-43-09-565_250x250.png\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3245622,\n" +
            "      \"pageURL\": \"https://pixabay.com/illustrations/question-mark-questions-and-answers-3245622/\",\n" +
            "      \"type\": \"illustration\",\n" +
            "      \"tags\": \"question mark, questions and answers, faq\",\n" +
            "      \"previewURL\": \"https://cdn.pixabay.com/photo/2018/03/21/05/29/question-mark-3245622_150.jpg\",\n" +
            "      \"previewWidth\": 150,\n" +
            "      \"previewHeight\": 150,\n" +
            "      \"webformatURL\": \"https://pixabay.com/get/g842b3de2230c4b3c6b13bef21751fef0bc8195aa735c5c36c67745b1ffdb8c9172ef5cf25b67d8dcff4a895d30de4c8e0771b708089b7f1035e29fa290cd5b94_640.jpg\",\n" +
            "      \"webformatWidth\": 640,\n" +
            "      \"webformatHeight\": 640,\n" +
            "      \"largeImageURL\": \"https://pixabay.com/get/geae6952a324510539f2b0fde7152ff91a4c790da640a215b32243b54fcbb99d8fd49e5ddd2d9761a2991ebd697b5e5bbf8de8c2d9bc0ebfd1bc3dec99b8f183a_1280.jpg\",\n" +
            "      \"imageWidth\": 2048,\n" +
            "      \"imageHeight\": 2048,\n" +
            "      \"imageSize\": 116048,\n" +
            "      \"views\": 13822,\n" +
            "      \"downloads\": 6259,\n" +
            "      \"collections\": 58,\n" +
            "      \"likes\": 54,\n" +
            "      \"comments\": 8,\n" +
            "      \"user_id\": 5229782,\n" +
            "      \"user\": \"mohamed_hassan\",\n" +
            "      \"userImageURL\": \"https://cdn.pixabay.com/user/2022/11/25/20-20-43-936_250x250.jpg\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2453534,\n" +
            "      \"pageURL\": \"https://pixabay.com/vectors/q-a-question-help-q-sign-support-2453534/\",\n" +
            "      \"type\": \"vector/ai\",\n" +
            "      \"tags\": \"q a, question, help\",\n" +
            "      \"previewURL\": \"https://cdn.pixabay.com/photo/2017/06/29/08/56/q-a-2453534_150.png\",\n" +
            "      \"previewWidth\": 150,\n" +
            "      \"previewHeight\": 85,\n" +
            "      \"webformatURL\": \"https://pixabay.com/get/ge95bccb0eb76b3b69be268a75ad29b48aa3a0079ad81411833c6d2ca65cba4b2f8f4c02f686b5fb3e96afb7942b71e8f0d85f330aeade0bc9a745c271b6191ad_640.png\",\n" +
            "      \"webformatWidth\": 640,\n" +
            "      \"webformatHeight\": 366,\n" +
            "      \"largeImageURL\": \"https://pixabay.com/get/g20b0cac79aab88de10e2687011b780a5b6b43bc926c0793332ca668802d92aa75c332fe6ead0b99b485a93b3a9d99434f55ee67076ed79b88701607254146cc1_1280.png\",\n" +
            "      \"imageWidth\": 1920,\n" +
            "      \"imageHeight\": 1099,\n" +
            "      \"imageSize\": 1205311,\n" +
            "      \"views\": 10848,\n" +
            "      \"downloads\": 5145,\n" +
            "      \"collections\": 29,\n" +
            "      \"likes\": 37,\n" +
            "      \"comments\": 11,\n" +
            "      \"user_id\": 5100055,\n" +
            "      \"user\": \"Josethestoryteller\",\n" +
            "      \"userImageURL\": \"https://cdn.pixabay.com/user/2017/06/15/08-29-16-278_250x250.jpg\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2870691,\n" +
            "      \"pageURL\": \"https://pixabay.com/photos/cocoa-sacks-jute-ghana-jabon-qa-2870691/\",\n" +
            "      \"type\": \"photo\",\n" +
            "      \"tags\": \"cocoa, sacks, jute\",\n" +
            "      \"previewURL\": \"https://cdn.pixabay.com/photo/2017/10/20/10/29/cocoa-28706921:46:27.6981_150.jpg\",\n" +
            "      \"previewWidth\": 150,\n" +
            "      \"previewHeight\": 100,\n" +
            "      \"webformatURL\": \"https://pixabay.com/get/gb612aa2bbdc790033871ae18813870fa1cff35c50e533539d30504cf1f83001658c1402b3366c50102a6c4f21d3ce293a6d7de99b184a8ef0f5563df021d1679_640.jpg\",\n" +
            "      \"webformatWidth\": 640,\n" +
            "      \"webformatHeight\": 428,\n" +
            "      \"largeImageURL\": \"https://pixabay.com/get/g9b0a1fa2d081f5e01462cea7c08b9b9f9b1a11dc5485125f1d4e4bbe9465b73f0deef842818051fb21c9c26f3e0c9ea0400f246f754dbf196ea704b2f4dfea6c_1280.jpg\",\n" +
            "      \"imageWidth\": 3872,\n" +
            "      \"imageHeight\": 2592,\n" +
            "      \"imageSize\": 2147737,\n" +
            "      \"views\": 4065,\n" +
            "      \"downloads\": 2070,\n" +
            "      \"collections\": 19,\n" +
            "      \"likes\": 8,\n" +
            "      \"comments\": 2,\n" +
            "      \"user_id\": 5671698,\n" +
            "      \"user\": \"5671698\",\n" +
            "      \"userImageURL\": \"\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}