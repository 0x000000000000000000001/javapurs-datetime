    // Port of Data/DateTime.js. The records are maps in this backend; JS's
    // Date.UTC normalizes out-of-range components, which the plus* chain below
    // reproduces. Years 0-99 keep their value.
    private static long __utcMillis(java.util.Map<String, Object> rec) {
        int year = ((Number) rec.get("year")).intValue();
        int month = ((Number) rec.get("month")).intValue();
        int day = ((Number) rec.get("day")).intValue();
        int hour = ((Number) rec.get("hour")).intValue();
        int minute = ((Number) rec.get("minute")).intValue();
        int second = ((Number) rec.get("second")).intValue();
        int millisecond = ((Number) rec.get("millisecond")).intValue();
        java.time.LocalDateTime moment = java.time.LocalDateTime.of(year, 1, 1, 0, 0)
            .plusMonths(month - 1L).plusDays(day - 1L)
            .plusHours(hour).plusMinutes(minute).plusSeconds(second)
            .plusNanos(millisecond * 1000000L);
        return moment.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
    }

    public static Object calcDiff = (java.util.function.Function<Object, Object>) (rec1) ->
        (java.util.function.Function<Object, Object>) (rec2) ->
            (double) (__utcMillis((java.util.Map<String, Object>) rec1) - __utcMillis((java.util.Map<String, Object>) rec2));

    public static Object adjustImpl = (java.util.function.Function<Object, Object>) (just) ->
        (java.util.function.Function<Object, Object>) (nothing) ->
        (java.util.function.Function<Object, Object>) (offset) ->
        (java.util.function.Function<Object, Object>) (rec) -> {
            double shifted = (double) __utcMillis((java.util.Map<String, Object>) rec) + ((Number) offset).doubleValue();
            try {
                java.time.LocalDateTime moment = java.time.Instant.ofEpochMilli((long) shifted)
                    .atOffset(java.time.ZoneOffset.UTC).toLocalDateTime();
                java.util.Map<String, Object> adjusted = new java.util.LinkedHashMap<>();
                adjusted.put("year", moment.getYear());
                adjusted.put("month", moment.getMonthValue());
                adjusted.put("day", moment.getDayOfMonth());
                adjusted.put("hour", moment.getHour());
                adjusted.put("minute", moment.getMinute());
                adjusted.put("second", moment.getSecond());
                adjusted.put("millisecond", moment.getNano() / 1000000);
                return ((java.util.function.Function<Object, Object>) just).apply(adjusted);
            } catch (RuntimeException invalid) {
                return nothing;
            }
        };
