# EasyClose
直接用@close方式实现对本包下资源的关闭操作，避免繁琐的
`  if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
`
